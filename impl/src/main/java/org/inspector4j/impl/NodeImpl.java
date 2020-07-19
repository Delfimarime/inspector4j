package org.inspector4j.impl;

import org.inspector4j.api.Node;
import org.inspector4j.core.Commons;

import java.util.HashMap;
import java.util.Map;

public class NodeImpl extends ObjectTypeNode implements Node {

    private Class<?> type;
    private Map<Node, Node> map;

    private NodeImpl() {
    }

    @Override
    protected Map<Node, Node> getMap() {
        return map;
    }

    @Override
    public Class<?> getType() {
        return this.type;
    }

    @Override
    public Map<Object, Object> asMap() {
        return toMap();
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public Map<Object, Object> toMap() {
        return (Map<Object, Object>) Commons.unwrap(this);
    }

    public static class Builder {

        private Class<?> type;
        private final Map<Node, Node> map;

        private Builder() {
            this.map = new HashMap<>();
        }

        public static Builder get() {
            return new Builder();
        }

        public Builder setType(Class<?> type) {
            if (type != null) {
                this.type = type;
            }
            return this;
        }

        public Builder set(String name, Node node) {
            if (name != null && node != null) {
                set(new BasicTypeNode(name), node);
            }
            return this;
        }

        public Builder set(Node key, Node node) {
            if (key != null && node != null) {
                this.map.put(key, node);
            }
            return this;
        }

        public Builder setAll(Map<Node, Node> map) {
            if (map != null) {
                this.map.putAll(map);
            }
            return this;
        }

        public Node build() {
            NodeImpl instance = new NodeImpl();

            if (type == null) {
                throw new IllegalArgumentException("Type mustn't be null");
            }

            if (map.isEmpty()) {
                throw new IllegalArgumentException("ObjectNode must have at least one field");
            }

            instance.type = type;
            instance.map = map;
            return instance;
        }

    }

}
