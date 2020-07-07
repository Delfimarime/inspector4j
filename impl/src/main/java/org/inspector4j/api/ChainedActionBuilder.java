package org.inspector4j.api;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public interface ChainedActionBuilder extends Action.Builder {

    ChainedActionBuilder next();

    @Override
    ChainedActionBuilder setCondition(BiPredicate<Object, Chain> condition);

    @Override
    ChainedActionBuilder setExecution(BiFunction<Object, Chain, Node> execution);

    Chain.Builder apply();

}
