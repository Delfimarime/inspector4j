package org.inspector4j.api.configuration;

import org.apache.commons.lang3.ClassUtils;
import org.inspector4j.SecretVisibility;
import org.inspector4j.api.configuration.xml.XmlConfigurationProvider;

import java.util.*;
import java.util.stream.Collectors;

public class ConfigurationManagerImpl implements ConfigurationManager {

    private final Queue<Inspector4JConfiguration> seq;
    private final InMemoryConfigurationProvider inMemory;

    public ConfigurationManagerImpl() {

        this.inMemory = new InMemoryConfigurationProvider();

        List<ConfigurationProvider> container = new ArrayList<>();

        container.add(inMemory);
        container.add(new XmlConfigurationProvider());


        ConfigurationProvider microprofile = newMicroprofile();

        if (microprofile != null) {
            container.add(2, microprofile);
        }

        container.add(new SystemConfigurationProvider());

        this.seq = container.stream().map(ConfigurationProvider::toProperties).filter(Objects::nonNull).collect(Collectors.toCollection(LinkedList::new));
    }

    public SecretVisibility getScope() {

        for (Inspector4JConfiguration configuration : seq) {
            SecretVisibility visibility = configuration.getRoot().getVisibility();

            if (visibility != null) {
                return visibility;
            }

        }

        return SecretVisibility.MASKED;
    }

    public SecretVisibility getScope(String name) {
        if (name == null) {
            return getScope();
        }

        for (Inspector4JConfiguration configuration : seq) {
            for (Map.Entry<String, InspectorConfiguration> each : configuration.getChildren().entrySet()) {
                if (each.getKey().equals(name)) {
                    SecretVisibility value = each.getValue().getVisibility();
                    if (value != null) {
                        return value;
                    }

                }
            }
        }

        return SecretVisibility.MASKED;
    }

    public boolean isOverridable() {
        for (Inspector4JConfiguration configuration : seq) {
            Boolean value = configuration.getRoot().getAllowRuntimeConfiguration();

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
            for (Map.Entry<String, InspectorConfiguration> each : configuration.getChildren().entrySet()) {
                if (each.getKey().equals(name)) {
                    Boolean value = each.getValue().getAllowRuntimeConfiguration();
                    if (value != null) {
                        return value;
                    }

                }
            }
        }

        return Boolean.FALSE;
    }

    @Override
    public ConfigurationManager setScope(SecretVisibility visibility) {
        inMemory.setSecretVisibility(visibility);
        return this;
    }

    @Override
    public ConfigurationManager setOverridable(Boolean override) {
        inMemory.setAllowRuntimeConfiguration(override);
        return this;
    }

    @Override
    public ConfigurationManager setScope(String name, SecretVisibility visibility) {
        inMemory.setSecretVisibility(name, visibility);
        return this;
    }

    @Override
    public ConfigurationManager setOverridable(String name, Boolean override) {
        inMemory.setAllowRuntimeConfiguration(name, override);
        return this;
    }

    @Override
    public ConfigurationManager setScope(Class<?> cls, SecretVisibility visibility) {
        return setScope(cls == null ? null : cls.getName(), visibility);
    }

    @Override
    public ConfigurationManager setOverridable(Class<?> cls, Boolean override) {
        return setOverridable(cls == null ? null : cls.getName(), override);
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
