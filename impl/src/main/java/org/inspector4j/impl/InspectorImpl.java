package org.inspector4j.impl;

import org.inspector4j.api.Inspector;
import org.inspector4j.api.Node;
import org.inspector4j.api.NodeFactory;

public class InspectorImpl implements Inspector {

    private final NodeFactory nodeFactory;

    public InspectorImpl() {
        this.nodeFactory = new NodeFactoryImpl();
    }

    @Override
    public Node inspect(Object object) {
        if(object instanceof Node){
            return (Node) object;
        }
        return null;
    }




}
