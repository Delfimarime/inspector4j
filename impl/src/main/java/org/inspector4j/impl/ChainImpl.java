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

    private final List<Action> sequence;
    private final Map<String, Node> cache;

    public ChainImpl(List<Action> sequence) {
        this.initialize();
        this.isSubContext = false;
        this.sequence = sequence;
        this.cache = new HashMap<>();
    }

    @Override
    public Node handle(Object object) {
        try {

            if (object != null && cache.containsKey(keyOf(object))) {
                return cache.get(keyOf(object));
            }

            if (bean != null && !bean.equals(object)) {
                isSubContext = Boolean.TRUE;
            }

            if (isSubContext) {
                int index = this.index;
                Object bean = this.bean;

                initialize();
                Node node = handle(object);
                initialize(bean, index, Boolean.TRUE);
                return node;
            }

            bean = object;
            return execute(object, () -> this.sequence.get(this.index++).handle(object, this));

        } catch (IndexOutOfBoundsException ex) {
            throw new InspectionException("No Action to handle the node transformation", ex);
        }
    }

    private void initialize() {
        initialize(null, 0, Boolean.FALSE);
    }

    private String keyOf(Object object) {
        if (object == null) {
            return null;
        }
        return object.getClass() + "@" + object.hashCode();
    }

    private void initialize(Object object, int index, boolean isSubContext) {
        this.index = index;
        this.bean = object;
        this.isSubContext = isSubContext;
    }

    private Node execute(Object object, Supplier<Node> factory) {

        String keyOf = keyOf(object);

        Node node = factory.get();

        if (keyOf != null && !cache.containsKey(keyOf)) {
            cache.put(keyOf, node);
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
        public ChainedActionBuilder newAction() {
            return new ChainedActionBuilderImpl(this);
        }

    }

}
