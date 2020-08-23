package org.inspector4j.api.configuration.xml;

import org.inspector4j.api.configuration.InspectorConfiguration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlNamedInspectorConfiguration extends XmlInspectorConfiguration {

    @XmlAttribute(name = "name")
    private String name;

    public XmlNamedInspectorConfiguration() {
    }

    public XmlNamedInspectorConfiguration(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
