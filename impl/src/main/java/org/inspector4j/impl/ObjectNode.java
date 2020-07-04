package org.inspector4j.impl;

import org.inspector4j.api.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ObjectNode implements Node {

    private Map<Node, Node> map;

    private ObjectNode() {
    }

    @Override
    public Node[] keys() {
        return map.keySet().toArray(new Node[0]);
    }

    @Override
    public Node get(Node key) {
        return map.get(key);
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public boolean isLong() {
        return false;
    }

    @Override
    public boolean isText() {
        return false;
    }

    @Override
    public boolean isInteger() {
        return false;
    }

    @Override
    public boolean isContainer() {
        return true;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public String asText() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long asLong() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean asBoolean() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int asInt() {
        throw new UnsupportedOperationException();
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

    @Override
    public String toString() {
        return "{ [" + map.entrySet().stream().map(each -> each.getKey() + " = " + each.getValue()).reduce((acc, v) -> acc.isEmpty() ? v : acc.concat(" , ").concat(v)).orElse("") + "]}";
    }

    public static class Builder {

        private final Map<Node, Node> map;

        private Builder() {
            this.map = new HashMap<>();
        }

        public static Builder get() {
            return new Builder();
        }

        public static Node create(Map<Node, Node> container) {
            if (container == null) {
                return new NullNode();
            }
            ObjectNode instance = new ObjectNode();
            instance.map = container;
            return instance;
        }


        public Builder addNode(String name, Node node) {
            if (name != null && node != null) {
                addNode(new ValueNode(name), node);
            }
            return this;
        }

        public Builder addNode(Node key, Node node) {
            if (key != null && node != null) {
                this.map.put(key, node);
            }
            return this;
        }

        public Node build() {
            ObjectNode instance = new ObjectNode();
            instance.map = map;
            return instance;
        }

    }

}
