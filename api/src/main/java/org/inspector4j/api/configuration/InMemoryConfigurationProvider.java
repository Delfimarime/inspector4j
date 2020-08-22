package org.inspector4j.api.configuration;

import org.inspector4j.api.Scope;

import java.util.ArrayList;

public class InMemoryConfigurationProvider implements ConfigurationProvider {

    private final Inspector4JConfiguration configuration;

    public InMemoryConfigurationProvider() {
        this.configuration = new Inspector4JConfiguration();
        this.configuration.setInspectors(new ArrayList<>());
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

        NamedConfiguration configuration = get(name);

        if (configuration == null) {
            configuration = new NamedConfiguration(name);
            this.configuration.getInspectors().add(configuration);
        }

        configuration.setScope(scope);
        return this;
    }

    public InMemoryConfigurationProvider setOverridable(String name, Boolean override) {
        if (name == null) {
            return setOverridable(override);
        }

        NamedConfiguration configuration = get(name);

        if (configuration == null) {
            configuration = new NamedConfiguration(name);
            this.configuration.getInspectors().add(configuration);
        }


        configuration.setOverridable(override);
        return this;
    }

    private NamedConfiguration get(String name) {
        return configuration.getInspectors().stream().filter(each -> each.getName().equals(name)).findFirst().orElse(null);
    }

}
