package org.inspector4j.api;

@FunctionalInterface
public interface Chain {

    Node handle(Object object);

    interface Builder {

        Chain build();

        Builder apply(Action action);

        Action.Builder newAction();

    }

}
