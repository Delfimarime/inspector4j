package org.inspector4j.api.configuration;

import org.inspector4j.Scope;

public interface Configuration {

    Scope getScope();

    boolean isOverridable();

}
