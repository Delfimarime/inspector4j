package org.inspector4j.api.internal;

import org.inspector4j.api.Inspector;
import org.inspector4j.api.configuration.Configuration;
import org.inspector4j.api.configuration.InspectorConfiguration;

public interface Factory {

    Inspector create(Configuration configuration);

}
