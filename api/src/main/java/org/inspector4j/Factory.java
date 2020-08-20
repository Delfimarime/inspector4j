package org.inspector4j;

import org.inspector4j.api.configuration.InspectorConfiguration;

public interface Factory {

    Inspector create(InspectorConfiguration configuration);

}
