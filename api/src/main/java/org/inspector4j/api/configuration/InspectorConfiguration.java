package org.inspector4j.api.configuration;

import org.inspector4j.api.Scope;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class InspectorConfiguration implements Serializable {


    @XmlAttribute(name = "scope")
    private Scope scope;

    @XmlAttribute(name = "override")
    private Boolean override;

    public InspectorConfiguration() {
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Boolean getOverridable() {
        return override;
    }

    public void setOverridable(Boolean override) {
        this.override = override;
    }

}
