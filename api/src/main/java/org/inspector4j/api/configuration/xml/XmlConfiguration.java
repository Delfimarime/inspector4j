package org.inspector4j.api.configuration.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Configuration")
public class XmlConfiguration implements Serializable {

    @XmlElement(name = "Root")
    private XmlInspectorConfiguration root;

    @XmlElement(name = "Inspector")
    private List<XmlNamedInspectorConfiguration> children;

    public XmlInspectorConfiguration getRoot() {
        return root;
    }

    public void setRoot(XmlInspectorConfiguration root) {
        this.root = root;
    }

    public List<XmlNamedInspectorConfiguration> getChildren() {
        return children;
    }

    public void setChildren(List<XmlNamedInspectorConfiguration> children) {
        this.children = children;
    }
}
