package org.inspector4j.impl;

import org.inspector4j.api.Analysis;
import org.inspector4j.api.Node;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AnalysisImpl extends ContainerNode implements Analysis {

    private Method method;
    private Map<Node, Node> map;

    @Override
    protected Map<Node, Node> getMap() {
        return map;
    }

    @Override
    public Class<?> getType() {
        return Analysis.class;
    }

    @Override
    public Node[] getArgs() {
        return map.values().toArray(new Node[0]);
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        AnalysisImpl analysis = (AnalysisImpl) object;
        return Objects.equals(method, analysis.method) &&
                Objects.equals(map, analysis.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), method, map);
    }

    public static class Builder {

        private Method method;
        private final Map<Node, Node> map;

        private Builder() {
            this.map = new HashMap<>();
        }

        public static Builder get() {
            return new Builder();
        }

        public Builder set(String name, Node node) {
            if (name != null && node != null) {
                set(new ValueNode(name), node);
            }
            return this;
        }

        public Builder set(Node key, Node node) {
            if (key != null && node != null) {
                this.map.put(key, node);
            }
            return this;
        }

        public Builder setAll(Map<Node, Node> map) {
            if (map != null) {
                this.map.putAll(map);
            }
            return this;
        }

        public Builder setMethod(Method method) {
            if (method != null) {
                this.method = method;
            }
            return this;
        }

        public Node build() {
            AnalysisImpl instance = new AnalysisImpl();

            if (method == null) {
                throw new IllegalArgumentException("Method mustn't be null");
            }

            instance.map = map;
            instance.method = method;

            return instance;
        }

    }
}
