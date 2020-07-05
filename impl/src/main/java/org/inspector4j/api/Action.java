package org.inspector4j.api;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public interface Action {
    Node handle(Object object, Chain chain);

    interface Builder {

        Builder setCondition(BiPredicate<Object, Chain> condition);

        Builder setCondition(BiFunction<Object, Chain, Node> condition);

        Action build();

    }
}
