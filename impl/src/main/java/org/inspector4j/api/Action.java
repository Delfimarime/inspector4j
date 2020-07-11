package org.inspector4j.api;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

@FunctionalInterface
public interface Action {

    Node handle(Object object, Chain chain);

    interface Builder  {

        Builder setCondition(BiPredicate<Object, Chain> condition);

        Builder setExecution(BiFunction<Object, Chain, Node> execution);

        Builder next();

        Action build();

        Chain.Builder apply();

    }

}
