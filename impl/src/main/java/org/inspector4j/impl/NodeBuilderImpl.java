package org.inspector4j.impl;

import org.inspector4j.api.Node;
import org.inspector4j.api.NodeFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NodeBuilderImpl implements Node.Builder {

    private Class<?> type;
    private final NodeFactory factory;
    private final Map<String, Node> cache;
    private final Map<String, Node> container;

    public NodeBuilderImpl(NodeFactory factory) {
        this(factory, null);
    }

    public NodeBuilderImpl(NodeFactory factory, Map<String, Node> cache) {
        this.factory = factory;
        this.container = new HashMap<>();
        this.cache = cache != null ? cache : new HashMap<>();
    }

    @Override
    public Node build() {
        Map<Node, Node> map = container.entrySet().stream().collect(Collectors.toMap(x -> (Node) factory.create(x.getKey()), x -> (Node) x.getValue()));
        return ObjectNode.Builder.get().setType(type).setAll(map).build();
    }

    @Override
    public Node.Builder setType(Class<?> type) {
        if (type != null) {
            this.type = type;
        }
        return this;
    }

    @Override
    public Node.Builder setNode(String fieldName, Node value) {
        return setProperty(fieldName, value, (object) -> object);
    }

    @Override
    public Node.Builder setNode(String fieldName, String value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, CharSequence value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, int value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Integer value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Long value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, long value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, char value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Character value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, float value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Float value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, double value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Double value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, boolean value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Boolean value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, byte value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Byte value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, BigDecimal value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, BigInteger value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Date value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, LocalTime value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, LocalDate value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, LocalDateTime value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, ZonedDateTime value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, String[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public <T> Node.Builder setNode(String fieldName, Collection<T> value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Method value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Class<?> value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Field value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Enum<?> value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, CharSequence[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, int[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Integer[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Long[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, long[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, char[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Character[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, float[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Float[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, double[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Double[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, boolean[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Boolean[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, byte[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Byte[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, BigDecimal[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, BigInteger[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Date[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Enum<?>[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, LocalTime[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, LocalDate[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, LocalDateTime[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, ZonedDateTime[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Method[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Class<?>[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    @Override
    public Node.Builder setNode(String fieldName, Field[] value) {
        return setProperty(fieldName, value, factory::create);
    }

    private <T> Node.Builder setProperty(String fieldName, T value, Function<T, Node> gen) {

        if (fieldName == null || value == null) {
            return this;
        }

        Node node;

        String key = value.getClass() + "@" + value.hashCode();

        if (cache.containsKey(key)) {
            node = cache.get(key);
        } else if (!(value instanceof Node)) {
            node = gen.apply(value);
            cache.put(key, node);
        } else {
            node = (Node) value;
            cache.put(key, node);
        }

        container.put(fieldName, node);
        return this;
    }


}
