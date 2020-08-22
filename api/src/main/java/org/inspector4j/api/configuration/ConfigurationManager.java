package org.inspector4j.api.configuration;

import org.apache.commons.lang3.ClassUtils;
import org.inspector4j.Scope;

import java.util.*;
import java.util.stream.Collectors;

public class ConfigurationManager {

    private final Queue<Inspector4JConfiguration> seq;
    private final InMemoryConfigurationProvider inMemory;

    public ConfigurationManager() {

        this.inMemory = new InMemoryConfigurationProvider();

        List<ConfigurationProvider> container = new ArrayList<>();

        container.add(inMemory);
        container.add(new XmlConfigurationProvider());
        container.add(new SystemConfigurationProvider());

        ConfigurationProvider microprofile = newMicroprofile();

        if (microprofile != null) {
            container.add(2, microprofile);
        }

        this.seq = container.stream().map(ConfigurationProvider::toProperties).filter(Objects::nonNull).collect(Collectors.toCollection(LinkedList::new));
    }

    public Scope getScope() {

        for (Inspector4JConfiguration configuration : seq) {
            Scope scope = configuration.getRoot().getScope();

            if (scope != null) {
                return scope;
            }

        }

        return Scope.ATTRIBUTE;
    }

    public Scope getScope(String name) {
        if (name == null) {
            return getScope();
        }

        for (Inspector4JConfiguration configuration : seq) {
            Scope scope = configuration.getInspectors().stream().filter(each -> each.getName().equals(name)).findFirst().map(InspectorConfiguration::getScope).orElse(null);

            if (scope != null) {
                return scope;
            }

        }

        return Scope.ATTRIBUTE;
    }

    public boolean isOverridable() {
        for (Inspector4JConfiguration configuration : seq) {
            Boolean value = configuration.getRoot().getOverridable();

            if (value != null) {
                return Boolean.FALSE;
            }

        }

        return Boolean.FALSE;
    }

    public boolean isOverridable(String name) {
        if (name == null) {
            return isOverridable();
        }

        for (Inspector4JConfiguration configuration : seq) {
            Boolean value = configuration.getInspectors().stream().filter(each -> each.getName().equals(name)).findFirst().map(each -> each.getOverridable()).orElse(null);

            if (value != null) {
                return value;
            }

        }

        return Boolean.FALSE;
    }

    public ConfigurationManager setScope(Scope scope) {
        inMemory.setScope(scope);
        return this;
    }

    public ConfigurationManager setOverridable(Boolean override) {
        inMemory.setOverridable(override);
        return this;
    }

    public ConfigurationManager setScope(String name, Scope scope) {
        inMemory.setScope(name, scope);
        return this;
    }

    public ConfigurationManager setOverridable(String name, Boolean override) {
        inMemory.setOverridable(name, override);
        return this;
    }

    private ConfigurationProvider newMicroprofile() {
        try {
            Class<?> returnType = ClassUtils.getClass("org.eclipse.microprofile.config.Config", false);
            return returnType != null ? new MicroprofileConfigurationProvider() : null;
        } catch (Exception ex) {
            return null;
        }
    }

}
