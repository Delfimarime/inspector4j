package org.inspector4j.api.configuration.xml;

import org.inspector4j.Scope;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlInspectorConfiguration implements Serializable {


    @XmlAttribute(name = "scope")
    private String scope;

    @XmlAttribute(name = "override")
    private String overridable;

    public XmlInspectorConfiguration() {
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getOverridable() {
        return overridable;
    }

    public void setOverridable(String override) {
        this.overridable = override;
    }

}
