package org.inspector4j.impl;


public class MaskedNode extends SingleTypeNode {

    private final Class<?> rootType;

    public MaskedNode(Class<?> rootType) {
        this.rootType = rootType;
    }

    @Override
    public Class<?> getType() {
        return rootType;
    }

}
