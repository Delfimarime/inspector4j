package org.inspector4j.api;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

@FunctionalInterface
public interface Action {

    Node handle(Object object, NodeMapper mapper);

    interface Builder  {

        Builder setCondition(BiPredicate<Object, NodeMapper> condition);

        Builder setExecution(BiFunction<Object, NodeMapper, Node> execution);

        Builder next();

        Action build();

        NodeMapper.Builder apply();

    }

}
