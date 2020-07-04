package org.inspector4j.impl;

import org.inspector4j.api.Node;
import org.inspector4j.api.NodeBuilder;
import org.inspector4j.api.NodeFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NodeBuilderImpl implements NodeBuilder {

    private final NodeFactory factory;
    private final Map<String, Node> cache;
    private final Map<String, Node> container;

    public NodeBuilderImpl(NodeFactory factory, Map<String, Node> cache) {
        this.factory = factory;
        this.container = new HashMap<>();
        this.cache = cache!=null ? cache:new HashMap<>();
    }

    @Override
    public Node build() {
        Map<Node, Node> map = container.entrySet().stream().collect(Collectors.toMap(x -> (Node) factory.build(x.getKey()), x -> (Node) x.getValue()));
        return ObjectNode.Builder.create(map);
    }

    @Override
    public NodeBuilder setNode(String fieldName, Node value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, String value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, CharSequence value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, int value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, Integer value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, Long value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, long value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, char value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, Character value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, float value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, Float value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, double value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, Double value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, boolean value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, Boolean value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, byte value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, Byte value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, BigDecimal value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, BigInteger value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, Date value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, Timestamp value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, LocalTime value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, LocalDate value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, LocalDateTime value) {
        return setProperty(fieldName, value);
    }

    @Override
    public NodeBuilder setNode(String fieldName, ZonedDateTime value) {
        return setProperty(fieldName, value);
    }

    @Override
    public <T> NodeBuilder setNode(String fieldName, T[] value) {
        return setProperty(fieldName, value);
    }

    @Override
    public <T, K> NodeBuilder setNode(String fieldName, Map<T, K> value) {
        return setProperty(fieldName, value);
    }

    @Override
    public <T> NodeBuilder setNode(String fieldName, Collection<T> value) {
        return setProperty(fieldName, value);
    }

    private NodeBuilder setProperty(String fieldName, Object value) {

        if (fieldName == null || value == null) {
            return this;
        }

        Node node = null;

        String key = value.getClass() + "@" + value.hashCode();

        if (cache.containsKey(key)) {
            node = cache.get(key);
        } else if (!(value instanceof Node)) {
            node = factory.build(value);
            cache.put(key, node);
        }else {
            node= (Node) value;
            cache.put(key, node);
        }

        container.put(fieldName, node);
        return this;
    }

}
