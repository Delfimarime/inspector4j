package org.inspector4j.impl;

import org.inspector4j.api.Action;
import org.inspector4j.api.Chain;
import org.inspector4j.api.Node;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class DefaultActionBuilder implements Action.Builder {

    private final Chain.Builder builder;
    private BiPredicate<Object, Chain> condition;
    private BiFunction<Object, Chain, Node> execution;

    public DefaultActionBuilder(Chain.Builder builder) {
        this.builder = builder;
    }

    @Override
    public Action.Builder next() {
         this.builder.apply(build());
         return new DefaultActionBuilder(builder);
    }

    @Override
    public Action.Builder setCondition(BiPredicate<Object, Chain> condition) {
        if (condition != null) {
            this.condition = condition;
        }
        return this;
    }

    @Override
    public Action.Builder setExecution(BiFunction<Object, Chain, Node> execution) {
        if (execution != null) {
            this.execution = execution;
        }
        return this;
    }

    @Override
    public Chain.Builder apply() {
        this.builder.apply(build());
        return builder;
    }

    @Override
    public Action build() {
        return ((object, chain) -> {
            if (condition != null && condition.test(object, chain)) {
                return execution.apply(object, chain);
            } else {
                return chain.handle(object);
            }
        });
    }

}
