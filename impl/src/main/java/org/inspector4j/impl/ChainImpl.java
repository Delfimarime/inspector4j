package org.inspector4j.impl;

import org.inspector4j.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ChainImpl implements Chain {

    private int index;
    private Object bean;
    private boolean isSubContext;

    private final List<Action> actions;
    private final Map<String, Node> beanCache;
    private final Map<String, Node> proxyCache;

    public ChainImpl(List<Action> actions) {
        this.initialize();
        this.isSubContext = false;
        this.actions = actions;
        this.beanCache = new HashMap<>();
        this.proxyCache = new HashMap<>();
    }

    @Override
    public Node handle(Object object) {
        try {
            String key = object == null ? null : keyOf(object);

            if (key != null && this.beanCache.containsKey(key)) {
                return this.beanCache.get(key);
            }

            if (key != null && this.proxyCache.containsKey(key)) {
                return this.proxyCache.get(key);
            }

            this.isSubContext = this.bean != null && !this.bean.equals(object);

            if (isSubContext) {
                return forSubContext(object);
            }

            this.bean = object;

            if (this.bean != null) {
                this.proxyCache.remove(keyOf(this.bean));
            }

            return this.execute(object, () -> this.actions.get(this.index++).handle(object, this));
        } catch (IndexOutOfBoundsException ex) {
            throw new InspectionException("No Action to handle the node transformation", ex);
        }
    }

    private String keyOf(Object object) {
        if (object == null) {
            return null;
        }
        return object.getClass() + "@" + object.hashCode();
    }

    private Node referenceOf(String key) {
        return new ReferenceNode(() -> beanCache.get(key));
    }

    private Node forSubContext(Object object) {
        int index = this.index;
        Object bean = this.bean;
        String bKey = bean == null ? null : keyOf(bean);

        if (bKey != null && !proxyCache.containsKey(bKey)) {
            proxyCache.put(bKey, referenceOf(bKey));
        }

        initialize();
        Node node = handle(object);
        initialize(bean, index, Boolean.TRUE);
        return node;
    }

    private void initialize() {
        initialize(null, 0, Boolean.FALSE);
    }

    private void initialize(Object object, int index, boolean isSubContext) {
        this.index = index;
        this.bean = object;
        this.isSubContext = isSubContext;
    }

    private Node execute(Object object, Supplier<Node> factory) {

        String keyOf = keyOf(object);

        Node node = factory.get();

        if (keyOf != null && !beanCache.containsKey(keyOf)) {
            beanCache.put(keyOf, node);
        }

        return node;
    }

    public static class Builder implements Chain.Builder {

        private final List<Action> sequence;

        private Builder() {
            this.sequence = new ArrayList<>();
        }

        public static Builder get() {
            return new Builder();
        }


        @Override
        public Chain build() {
            return new ChainImpl(sequence);
        }


        @Override
        public Chain.Builder apply(Action action) {
            if (action != null) {
                sequence.add(action);
            }
            return this;
        }

        @Override
        public Action.Builder newAction() {
            return new DefaultActionBuilder(this);
        }

    }

}
