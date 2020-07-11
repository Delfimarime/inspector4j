package org.inspector4j;

import org.inspector4j.api.InspectionResult;
import org.inspector4j.api.Node;

import java.lang.reflect.Method;

public interface Inspector {

    /**
     * Inspect the argument and creates a node which represents the argument
     * @param arg Argument which node representation is required
     * @return the argument representation as node
     */
    Node inspect(Object arg);

    /**
     * Inspects an method and arguments as a Node
     * @param method Method indented to be inspected
     * @param args Arguments supplied to the method
     * @return The representation as a {@link Node}
     */
    InspectionResult inspect(Method method, Object[] args);

}
