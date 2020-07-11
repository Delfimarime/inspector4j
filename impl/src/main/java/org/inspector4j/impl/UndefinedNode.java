package org.inspector4j.impl;

import org.inspector4j.api.Node;

public class UndefinedNode extends SingleTypeNode implements Node {

    private final Class<?> rootType;

    public UndefinedNode(){
        this(Object.class);
    }

    public UndefinedNode(Class<?> objectClass) {
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

}
