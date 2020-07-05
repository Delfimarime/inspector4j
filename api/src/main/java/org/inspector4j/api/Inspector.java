package org.inspector4j.api;

public interface Inspector {

    /**
     * Inspect the argument and creates a node which represents the argument
     * @param arg - Argument which node representation is required
     * @return the argument representation as node
     */
    Node inspect(Object arg);

}
