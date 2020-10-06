package org.inspector4j.api.configuration;

import org.inspector4j.SecretVisibility;

import java.util.HashMap;

public class InMemoryConfigurationProvider implements ConfigurationProvider {

    private final Inspector4JConfiguration configuration;

    public InMemoryConfigurationProvider() {
        this.configuration = new Inspector4JConfiguration();
        this.configuration.setChildren(new HashMap<>());
        this.configuration.setRoot(new InspectorConfiguration());
    }

    @Override
    public Inspector4JConfiguration toProperties() {
        return configuration;
    }

    public InMemoryConfigurationProvider setSecretVisibility(SecretVisibility visibility) {
        configuration.getRoot().setVisibility(visibility);
        return this;
    }

    public InMemoryConfigurationProvider setAllowRuntimeConfiguration(Boolean override) {
        configuration.getRoot().setAllowRuntimeConfiguration(override);
        return this;
    }

    public InMemoryConfigurationProvider setSecretVisibility(String name, SecretVisibility visibility) {
        if (name == null) {
            return setSecretVisibility(visibility);
        }

        InspectorConfiguration configuration = this.configuration.getChildren().get(name);

        if (configuration == null) {
            configuration = new InspectorConfiguration();
            this.configuration.getChildren().put(name, configuration);
        }

        configuration.setVisibility(visibility);
        return this;
    }

    public InMemoryConfigurationProvider setAllowRuntimeConfiguration(String name, Boolean override) {
        if (name == null) {
            return setAllowRuntimeConfiguration(override);
        }

        InspectorConfiguration configuration = this.configuration.getChildren().get(name);

        if (configuration == null) {
            configuration = new InspectorConfiguration();
            this.configuration.getChildren().put(name, configuration);
        }


        configuration.setAllowRuntimeConfiguration(override);
        return this;
    }


}
