package org.inspector4j;

import org.inspector4j.api.InspectionResult;
import org.inspector4j.api.configuration.Configuration;

import java.lang.reflect.Method;

public interface Adapter {

    InspectionResult inspect(Configuration configuration, Method method, Object[] args);

    InspectionResult inspect(Configuration configuration, Method method, Object[] args, SecretVisibility visibility);

}
