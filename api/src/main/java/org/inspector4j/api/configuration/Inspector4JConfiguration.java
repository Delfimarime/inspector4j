package org.inspector4j.api.configuration;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Configuration")
public class Inspector4JConfiguration implements Serializable {

    @XmlElement(name = "Root")
    private InspectorConfiguration root;

    @XmlElement(name = "Inspector")
    private List<NamedConfiguration> inspectors;

    public InspectorConfiguration getRoot() {
        return root;
    }

    public void setRoot(InspectorConfiguration root) {
        this.root = root;
    }

    public List<NamedConfiguration> getInspectors() {
        return inspectors;
    }

    public void setInspectors(List<NamedConfiguration> inspectors) {
        this.inspectors = inspectors;
    }

}
