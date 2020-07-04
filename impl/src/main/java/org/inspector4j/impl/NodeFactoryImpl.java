package org.inspector4j.impl;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.inspector4j.api.InspectionException;
import org.inspector4j.api.Node;
import org.inspector4j.api.NodeBuilder;
import org.inspector4j.api.NodeFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked"})
public class NodeFactoryImpl implements NodeFactory {

    private final NullNode emptyNode;

    public NodeFactoryImpl() {
        this.emptyNode = new NullNode();
    }

    @Override
    public Node build() {
        return emptyNode;
    }

    @Override
    public Node build(String value) {
        return asValue(value);
    }

    @Override
    public Node build(CharSequence value) {
        return asValue(value);
    }

    @Override
    public Node build(int value) {
        return asValue(value);
    }

    @Override
    public Node build(Integer value) {
        return asValue(value);
    }

    @Override
    public Node build(Long value) {
        return asValue(value);
    }

    @Override
    public Node build(long value) {
        return asValue(value);
    }

    @Override
    public Node build(char value) {
        return asValue(value);
    }

    @Override
    public Node build(Character value) {
        return asValue(value);
    }

    @Override
    public Node build(float value) {
        return asValue(value);
    }

    @Override
    public Node build(Float value) {
        return asValue(value);
    }

    @Override
    public Node build(double value) {
        return asValue(value);
    }

    @Override
    public Node build(Double value) {
        return asValue(value);
    }

    @Override
    public Node build(boolean value) {
        return asValue(value);
    }

    @Override
    public Node build(Boolean value) {
        return asValue(value);
    }

    @Override
    public Node build(byte value) {
        return asValue(value);
    }

    @Override
    public Node build(Byte value) {
        return asValue(value);
    }

    @Override
    public Node build(BigDecimal value) {
        return asValue(value);
    }

    @Override
    public Node build(BigInteger value) {
        return asValue(value);
    }

    @Override
    public Node build(Date value) {
        return asValue(value);
    }

    @Override
    public Node build(Timestamp value) {
        return asValue(value);
    }

    @Override
    public Node build(LocalTime value) {
        return asValue(value);
    }

    @Override
    public Node build(LocalDate value) {
        return asValue(value);
    }

    @Override
    public Node build(LocalDateTime value) {
        return asValue(value);
    }

    @Override
    public Node build(ZonedDateTime value) {
        return asValue(value);
    }

    @Override
    public <T> Node build(T[] value) {
        if (value == null) {
            return build();
        }
        return new IterableNode(Arrays.stream(value).map(this::build).toArray(Node[]::new));
    }

    @Override
    public <T, K> Node build(Map<T, K> object) {
        Map<Node, Node> container = object.entrySet().stream().collect(Collectors.toMap(this::build, this::build));
        return ObjectNode.Builder.create(container);
    }

    @Override
    public <T> Node build(Collection<T> object) {
        return new IterableNode(((Collection<?>) object).stream().map(this::build).toArray(Node[]::new));
    }

    @Override
    public NodeBuilder builder() {
        return new NodeBuilderImpl(this, new HashMap<>());
    }

    @Override
    public Node build(Object object) {

        if (object == null) {
            return emptyNode;
        }

        if (object instanceof Node) {
            return (Node) object;
        }

        if (object instanceof Map) {
            return build((Map<?, ?>) object);
        }

        if (object instanceof Collection<?>) {
            return build((Collection<?>) object);
        }

        Class<?> cls = object.getClass();

        if (isEqualAny(cls, int.class, Integer.class)) {
            return build((Integer) object);
        }

        if (isEqualAny(cls, String.class)) {
            return build((String) object);
        }

        if (isEqualAny(cls, CharSequence.class)) {
            return build((CharSequence) object);
        }

        if (isEqualAny(cls, char.class, Character.class)) {
            return build((Character) object);
        }

        if (isEqualAny(cls, long.class, Long.class)) {
            return build((Long) object);
        }

        if (isEqualAny(cls, float.class, Float.class)) {
            return build((Float) object);
        }

        if (isEqualAny(cls, double.class, Double.class)) {
            return build((Double) object);
        }

        if (isEqualAny(cls, byte.class, Byte.class)) {
            return build((Byte) object);
        }

        if (isEqualAny(cls, boolean.class, Boolean.class)) {
            return build((Byte) object);
        }

        if (isEqualAny(cls, BigDecimal.class)) {
            return build((BigDecimal) object);
        }

        if (isEqualAny(cls, BigInteger.class)) {
            return build((BigInteger) object);
        }

        if (isEqualAny(cls, Timestamp.class)) {
            return build((Timestamp) object);
        }

        if (isEqualAny(cls, Date.class)) {
            return build((Date) object);
        }

        if (isEqualAny(cls, LocalTime.class)) {
            return build((LocalTime) object);
        }

        if (isEqualAny(cls, LocalDate.class)) {
            return build((LocalDate) object);
        }

        if (isEqualAny(cls, LocalDateTime.class)) {
            return build((LocalDateTime) object);
        }

        if (isEqualAny(cls, ZonedDateTime.class)) {
            return build((ZonedDateTime) object);
        }

        if (cls.isArray()) {
            return build(asArray(object));
        }

        return asObjectNode(object);
    }

    private Node asObjectNode(Object value) {
        try {

            NodeBuilder builder = builder();

            for (Field field : FieldUtils.getAllFieldsList(value.getClass())) {

                Object readField = FieldUtils.readField(field, value, true);

                if (readField == null) {
                    builder.setNode(field.getName(), emptyNode);
                } else {
                    builder.setNode(field.getName(), build(readField));
                }
            }

            return builder.build();
        } catch (IllegalAccessException ex) {
            throw new InspectionException(ex);
        }
    }

    private boolean isEqualAny(Class<?> cls, Class<?>... anyOf) {
        if (cls == null && anyOf == null) {
            return true;
        } else if (cls == null) {
            return false;
        } else if (anyOf == null) {
            return false;
        } else {
            return Arrays.asList(anyOf).contains(cls);
        }
    }

    private <T> T[] asArray(Object readField) {
        return (T[]) readField;
    }

    private Node asValue(Object value) {
        return value == null ? build() : new ValueNode(value);
    }

}
