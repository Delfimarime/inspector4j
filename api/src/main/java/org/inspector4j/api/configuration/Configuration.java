package org.inspector4j.api.configuration;

import org.inspector4j.SecretVisibility;

public interface Configuration {

    SecretVisibility getScope();

    boolean isOverridable();

}
