package org.inspector4j.api;

@FunctionalInterface
public interface NodeMapper {

    Node handle(Object object);

    interface Builder {

        NodeMapper build();

        Builder apply(Action action);

        Action.Builder newAction();

    }

}
