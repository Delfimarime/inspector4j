package org.inspector4j.core;

import org.inspector4j.api.Node;

import java.util.*;

public class DefaultHandlingContext implements HandlingContext {

    private int index;
    private Object object;
    private List<Handler> handlers;
    private Map<String, Node> cache;
    private HandlingContext.Builder.Factory factory;

    private DefaultHandlingContext() {
    }

    @Override
    public Node proceed() {

        if (index < 0 || index > handlers.size() - 1) {
            throw new NoSuchElementException("Cannot proceed due to no handler available for type " + object.getClass());
        }

        Node node = handlers.get(index++).handle(this, object);

        if (node != null && object != null) {
            cache.put(object.getClass().getName() + "@" + object.hashCode(), node);
        }

        return node;
    }

    @Override
    public Node get(Object obj) {
        if (obj == null) {
            return null;
        }

        String key = obj.getClass().getName() + "@" + obj.hashCode();

        return cache.get(key);
    }

    @Override
    public HandlingContext.Builder.Factory getFactory() {
        return this.factory;
    }

    static class Builder implements HandlingContext.Builder {

        private Object object;
        private Factory factory;
        private List<Handler> handlers;
        private Map<String, Node> cache;

        private Builder() {
        }

        @Override
        public HandlingContext.Builder setObject(Object obj) {
            this.object = obj;
            return this;
        }

        @Override
        public HandlingContext build() {
            DefaultHandlingContext instance = new DefaultHandlingContext();
            instance.index = 0;
            instance.cache = cache;
            instance.object = object;
            instance.handlers = handlers;
            instance.factory = this.factory;
            return instance;
        }

        static class Factory implements HandlingContext.Builder.Factory {

            private final List<Handler> handlers;
            private final Map<String, Node> cache = new HashMap<>();

            public Factory(List<Handler> handlers) {
                this.handlers = Collections.unmodifiableList(handlers);
            }

            @Override
            public HandlingContext.Builder get() {
                Builder instance = new Builder();
                instance.cache = cache;
                instance.factory = this;
                instance.handlers = handlers;
                return instance;
            }

        }

    }

}
