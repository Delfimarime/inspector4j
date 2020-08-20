package org.inspector4j;

import org.inspector4j.api.InspectionResult;
import org.inspector4j.api.configuration.InspectorConfiguration;
import org.inspector4j.api.Node;

import java.lang.reflect.Method;

public interface Inspector {

    /**
     * Inspects an method and arguments as a Node
     *
     * @param method Method indented to be inspected
     * @param args   Arguments supplied to the method
     * @return The representation as a {@link Node}
     */
    InspectionResult inspect(Method method, Object[] args);

    /**
     * Inspects an method and arguments as a Node
     *
     * @param method Method indented to be inspected
     * @param args   Arguments supplied to the method
     * @param inspectAll Enables secrets to be disable causing secrets to be inspected
     * @return The representation as a {@link Node}
     */
    InspectionResult inspect(Method method, Object[] args, boolean inspectAll);

}
