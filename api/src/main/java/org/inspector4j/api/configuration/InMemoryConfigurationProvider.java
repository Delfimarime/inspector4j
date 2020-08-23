package org.inspector4j.api.configuration;

import org.inspector4j.Scope;

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

    public InMemoryConfigurationProvider setScope(Scope scope) {
        configuration.getRoot().setScope(scope);
        return this;
    }

    public InMemoryConfigurationProvider setOverridable(Boolean override) {
        configuration.getRoot().setOverridable(override);
        return this;
    }

    public InMemoryConfigurationProvider setScope(String name, Scope scope) {
        if (name == null) {
            return setScope(scope);
        }

        InspectorConfiguration configuration = this.configuration.getChildren().get(name);

        if (configuration == null) {
            configuration = new InspectorConfiguration();
            this.configuration.getChildren().put(name, configuration);
        }

        configuration.setScope(scope);
        return this;
    }

    public InMemoryConfigurationProvider setOverridable(String name, Boolean override) {
        if (name == null) {
            return setOverridable(override);
        }

        InspectorConfiguration configuration = this.configuration.getChildren().get(name);

        if (configuration == null) {
            configuration = new InspectorConfiguration();
            this.configuration.getChildren().put(name, configuration);
        }


        configuration.setOverridable(override);
        return this;
    }


}
