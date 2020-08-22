package org.inspector4j.api.configuration;

import org.inspector4j.Scope;

public interface ConfigurationManager {

    ConfigurationManager setScope(Scope scope);

    ConfigurationManager setOverridable(Boolean override);

    ConfigurationManager setScope(String name, Scope scope);

    ConfigurationManager setOverridable(String name, Boolean override);

    ConfigurationManager setScope(Class<?> cls, Scope scope);

    ConfigurationManager setOverridable(Class<?> cls, Boolean override);

}
