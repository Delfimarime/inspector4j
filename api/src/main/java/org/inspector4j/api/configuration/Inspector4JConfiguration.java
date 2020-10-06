package org.inspector4j.api.configuration;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Inspector4JConfiguration implements Serializable {

    private InspectorConfiguration root;

    private Map<String,InspectorConfiguration> children;

    public InspectorConfiguration getRoot() {
        return root;
    }

    public void setRoot(InspectorConfiguration root) {
        this.root = root;
    }

    public Map<String, InspectorConfiguration> getChildren() {
        return children;
    }

    public void setChildren(Map<String, InspectorConfiguration> children) {
        this.children = children;
    }

}
