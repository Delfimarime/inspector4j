package org.inspector4j.impl;

import org.inspector4j.Factory;
import org.inspector4j.Inspector;
import org.inspector4j.api.configuration.InspectorConfiguration;
import org.inspector4j.core.InspectorImpl;

public class FactoryImpl implements Factory {

    @Override
    public Inspector create(InspectorConfiguration configuration) {

        if (configuration == null) {
            throw new IllegalArgumentException("Configuration mustn't be null");
        }

        return new InspectorImpl(configuration.getScope(), configuration.isDynamic());

    }

}
