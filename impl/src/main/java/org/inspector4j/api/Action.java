package org.inspector4j.api;

import java.util.function.BiPredicate;

@FunctionalInterface
public interface Action {

    Node map(Context context, NodeCreator mapper, Object object);

    interface Builder {

        Builder setCondition(BiPredicate<Object, NodeCreator> condition);

        Builder setExecution(TriFunction<Context, NodeCreator, Object, Node> execution);

        Builder next();

        Action build();

        NodeCreator.Builder apply();

    }

}
