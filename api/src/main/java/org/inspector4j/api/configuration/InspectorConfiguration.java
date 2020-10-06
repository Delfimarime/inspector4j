package org.inspector4j.api.configuration;

import org.inspector4j.SecretVisibility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class InspectorConfiguration implements Serializable {

    public static final String VISIBILITY_FIELD = "secrets-visibility";
    public static final String ALLOW_RUNTIME_CONFIGURATION_FIELD = "secret-visibility";

    @XmlAttribute(name = VISIBILITY_FIELD)
    private SecretVisibility visibility;

    @XmlAttribute(name = ALLOW_RUNTIME_CONFIGURATION_FIELD)
    private Boolean allowRuntimeConfiguration;

    public InspectorConfiguration() {
    }

    public SecretVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(SecretVisibility visibility) {
        this.visibility = visibility;
    }

    public Boolean getAllowRuntimeConfiguration() {
        return allowRuntimeConfiguration;
    }

    public void setAllowRuntimeConfiguration(Boolean override) {
        this.allowRuntimeConfiguration = override;
    }

}
