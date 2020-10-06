package org.inspector4j.api.configuration;

import org.inspector4j.SecretVisibility;

public interface ConfigurationManager {

    ConfigurationManager setScope(SecretVisibility visibility);

    ConfigurationManager setOverridable(Boolean override);

    ConfigurationManager setScope(String name, SecretVisibility visibility);

    ConfigurationManager setOverridable(String name, Boolean override);

    ConfigurationManager setScope(Class<?> cls, SecretVisibility visibility);

    ConfigurationManager setOverridable(Class<?> cls, Boolean override);

}
