package org.inspector4j.core;

import org.inspector4j.api.Node;

@FunctionalInterface
public interface Handler {

    Node handle(HandlingContext context, Object object);

}
