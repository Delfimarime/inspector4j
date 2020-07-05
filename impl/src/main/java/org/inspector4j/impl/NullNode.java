package org.inspector4j.impl;

import org.inspector4j.api.Node;

public class NullNode extends SingularNode implements Node {

    private final Class<?> rootType;

    public NullNode(){
        this(Object.class);
    }

    public NullNode(Class<?> objectClass) {
        this.rootType = objectClass;
    }

    @Override
    public Class<?> getType() {
        return rootType;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String toString() {
        return "{}";
    }

}
