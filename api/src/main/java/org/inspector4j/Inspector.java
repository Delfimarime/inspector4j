package org.inspector4j;

import org.inspector4j.api.Analysis;
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
    Analysis inspect(Method method, Object[] args);

    /**
     * Inspects an method and arguments as a Node
     * @param beanClass The class which this method has been invoked on
     * @param method Method indented to be inspected
     * @param args Arguments supplied to the method
     * @return The representation as a {@link Node}
     */
    Analysis inspect(Class<?> beanClass, Method method, Object[] args);

}
