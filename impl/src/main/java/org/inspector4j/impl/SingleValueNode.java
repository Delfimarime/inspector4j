package org.inspector4j.impl;

import org.inspector4j.api.Node;

public abstract class SingleValueNode implements Node {

    @Override
    public Node[] keys() {
        return new Node[0];
    }

    @Override
    public Node get(Node key) {
        throw new UnsupportedOperationException();
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
    public boolean isInteger() {
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
    public boolean isContainer() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public int size() {
        return 0;
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

}
