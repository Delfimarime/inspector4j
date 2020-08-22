package org.inspector4j;

import org.inspector4j.api.InspectionResult;
import org.inspector4j.api.Scope;
import org.inspector4j.api.configuration.InspectorConfiguration;

import java.lang.reflect.Method;

public interface Adapter {

    InspectionResult inspect(InspectorConfiguration configuration, Method method, Object[] args);

    InspectionResult inspect(InspectorConfiguration configuration, Method method, Object[] args, Scope scope);

}
