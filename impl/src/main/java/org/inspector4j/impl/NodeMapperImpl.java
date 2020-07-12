package org.inspector4j.impl;

import org.inspector4j.api.Action;
import org.inspector4j.api.InspectionException;
import org.inspector4j.api.Node;
import org.inspector4j.api.NodeMapper;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class NodeMapperImpl implements NodeMapper {

    private int index;
    private Object bean;
    private boolean isFirst;

    private final List<Action> actions;
    private final Map<String, Node> beanCache;
    private final Map<String, Node> proxyCache;

    public NodeMapperImpl(List<Action> actions) {
        this.initialize();
        this.isFirst = false;
        this.actions = actions;
        this.beanCache = new HashMap<>();
        this.proxyCache = new HashMap<>();
    }

    @Override
    public Node map(Object object) {
        try {
            String key = object == null ? null : keyOf(object);

            if (key != null && this.beanCache.containsKey(key)) {
                return this.beanCache.get(key);
            }

            if (key != null && this.proxyCache.containsKey(key)) {
                return this.proxyCache.get(key);
            }

            this.isFirst = this.bean != null && !this.bean.equals(object);

            if (isFirst) {
                return asFirst(object);
            }

            this.bean = object;

            if (this.bean != null) {
                this.proxyCache.remove(keyOf(this.bean));
            }

            return this.intercept(object, () -> this.actions.get(this.index++).handle(object, this));
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

    private Node instanceOf(String key) {
        return (Node) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Node.class}, (obj, method, args) -> method.invoke(beanCache.get(key), args));
    }

    private Node asFirst(Object object) {
        int index = this.index;
        Object bean = this.bean;
        String key = bean == null ? null : keyOf(bean);

        if (key != null && !proxyCache.containsKey(key)) {
            proxyCache.put(key, instanceOf(key));
        }

        initialize();
        Node node = map(object);
        initialize(bean, index, Boolean.TRUE);
        return node;
    }

    private void initialize() {
        initialize(null, 0, Boolean.FALSE);
    }

    private void initialize(Object object, int index, boolean isSubContext) {
        this.index = index;
        this.bean = object;
        this.isFirst = isSubContext;
    }

    private Node intercept(Object object, Supplier<Node> factory) {

        String keyOf = keyOf(object);

        Node node = factory.get();

        if (keyOf != null && !beanCache.containsKey(keyOf)) {
            beanCache.put(keyOf, node);
        }

        return node;
    }

    public static class Builder implements NodeMapper.Builder {

        private final List<Action> sequence;

        private Builder() {
            this.sequence = new ArrayList<>();
        }

        public static Builder get() {
            return new Builder();
        }


        @Override
        public NodeMapper build() {
            return new NodeMapperImpl(sequence);
        }


        @Override
        public NodeMapper.Builder apply(Action action) {
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
