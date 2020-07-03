package org.inspector4j.impl;

import org.inspector4j.api.Node;

import java.util.Objects;

public class ValueNode extends SingleValueNode implements Node {

    private Object value;

    public ValueNode(Object value) {
        this.value = value;
    }

    @Override
    public boolean isNull() {
        return value == null;
    }

    @Override
    public boolean isBoolean() {
        return value instanceof Boolean;
    }

    @Override
    public boolean isArray() {
        return value.getClass().isArray();
    }

    @Override
    public boolean isDouble() {
        return value instanceof Double;
    }

    @Override
    public boolean isInteger() {
        return value instanceof Integer;
    }

    @Override
    public boolean isText() {
        return value instanceof String || value instanceof Character;
    }

    @Override
    public String asText() {
        if (!isText()) {
            throw new UnsupportedOperationException();
        }

        if(value instanceof Character){
            return value.toString();
        }

        return (String) value;
    }

    @Override
    public Long asLong() {
        if(!isLong()){
            throw new UnsupportedOperationException();
        }
        return (Long) value;
    }

    @Override
    public boolean asBoolean() {
        if(!isBoolean()){
            throw new UnsupportedOperationException();
        }
        return (boolean) value;
    }

    @Override
    public int asInt() {
        if(!isInteger()){
            throw new UnsupportedOperationException();
        }
        return (int) value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueNode valueNode = (ValueNode) o;
        return Objects.equals(value, valueNode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
