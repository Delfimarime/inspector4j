package org.inspector4j.api;

import org.inspector4j.api.configuration.InspectorConfiguration;

public interface Factory {

    Inspector create(InspectorConfiguration configuration);

}
