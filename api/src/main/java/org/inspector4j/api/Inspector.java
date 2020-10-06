package org.inspector4j.api;

import org.inspector4j.api.internal.Node;

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
