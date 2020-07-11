package org.inspector4j.impl;

import org.inspector4j.api.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectNode extends ContainerNode implements Node {

    private Class<?> type;
    private Map<Node, Node> map;

    private ObjectNode() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectNode that = (ObjectNode) o;
        return Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
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
                set(new ValueNode(name), node);
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
            ObjectNode instance = new ObjectNode();

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
