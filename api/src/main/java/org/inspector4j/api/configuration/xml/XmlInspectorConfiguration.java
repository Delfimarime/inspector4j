package org.inspector4j.api.configuration.xml;

import org.inspector4j.api.configuration.InspectorConfiguration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlInspectorConfiguration implements Serializable {


    @XmlAttribute(name = InspectorConfiguration.VISIBILITY_FIELD)
    private String secretsVisibility;

    @XmlAttribute(name = InspectorConfiguration.ALLOW_RUNTIME_CONFIGURATION_FIELD)
    private String allowRuntimeConfiguration;

    public XmlInspectorConfiguration() {
    }

    public String getSecretsVisibility() {
        return secretsVisibility;
    }

    public void setSecretsVisibility(String secretsVisibility) {
        this.secretsVisibility = secretsVisibility;
    }

    public String getAllowRuntimeConfiguration() {
        return allowRuntimeConfiguration;
    }

    public void setAllowRuntimeConfiguration(String override) {
        this.allowRuntimeConfiguration = override;
    }

}
