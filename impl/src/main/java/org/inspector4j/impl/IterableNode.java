package org.inspector4j.impl;

import org.inspector4j.api.Node;

import java.util.Arrays;

public class IterableNode implements Node {

    private Node[] container;

    public IterableNode(Node[] container) {
        this.container = container;
    }

    @Override
    public Node[] keys() {
        Node[] keys = new Node[size()];

        if (keys.length > 0) {
            for (int index = 0; index < keys.length; index++) {
                keys[index] = new ValueNode(index);
            }
        }

        return keys;
    }

    @Override
    public Node get(Node key) {
        if (!key.isInteger()) {
            return null;
        }
        return container[key.asInt()];
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
        return container.length;
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
        IterableNode that = (IterableNode) o;
        return Arrays.equals(container, that.container);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(container);
    }

}
