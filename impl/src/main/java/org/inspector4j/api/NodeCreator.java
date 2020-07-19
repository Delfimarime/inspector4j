package org.inspector4j.api;

public interface NodeCreator {

    Node map(Context context, Object object);

    interface Builder {

        NodeCreator build();

        Builder apply(Action action);

        Action.Builder newAction();

    }

}
