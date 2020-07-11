package org.inspector4j.impl;

import org.inspector4j.api.Action;
import org.inspector4j.api.NodeMapper;
import org.inspector4j.api.Node;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class DefaultActionBuilder implements Action.Builder {

    private final NodeMapper.Builder builder;
    private BiPredicate<Object, NodeMapper> condition;
    private BiFunction<Object, NodeMapper, Node> execution;

    public DefaultActionBuilder(NodeMapper.Builder builder) {
        this.builder = builder;
    }

    @Override
    public Action.Builder next() {
         this.builder.apply(build());
         return new DefaultActionBuilder(builder);
    }

    @Override
    public Action.Builder setCondition(BiPredicate<Object, NodeMapper> condition) {
        if (condition != null) {
            this.condition = condition;
        }
        return this;
    }

    @Override
    public Action.Builder setExecution(BiFunction<Object, NodeMapper, Node> execution) {
        if (execution != null) {
            this.execution = execution;
        }
        return this;
    }

    @Override
    public NodeMapper.Builder apply() {
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
