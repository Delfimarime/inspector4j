package org.inspector4j.impl;

import org.inspector4j.api.Node;

public class NullNode extends SingleValueNode implements Node {

    @Override
    public boolean isNull() {
        return true;
    }

}
