package org.inspector4j.impl;

import org.inspector4j.api.Node;
import org.inspector4j.api.NodeFactory;
import org.inspector4j.api.UnsupportedTypeException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@SuppressWarnings({"unchecked"})
public class NodeFactoryImpl implements NodeFactory {

    private final NullNode emptyNode;

    public NodeFactoryImpl() {
        this.emptyNode = new NullNode();
    }

    @Override
    public Node create() {
        return emptyNode;
    }

    @Override
    public Node nullOf(Class<?> type) {
        return new NullNode(type);
    }

    @Override
    public Node create(String value) {
        return asValue(String.class, value);
    }

    @Override
    public Node create(CharSequence value) {
        return asValue(CharSequence.class, value);
    }

    @Override
    public Node create(int value) {
        return asValue(int.class, value);
    }

    @Override
    public Node create(Integer value) {
        return asValue(Integer.class, value);
    }

    @Override
    public Node create(Long value) {
        return asValue(Long.class, value);
    }

    @Override
    public Node create(long value) {
        return asValue(long.class, value);
    }

    @Override
    public Node create(char value) {
        return asValue(char.class, value);
    }

    @Override
    public Node create(Character value) {
        return asValue(Character.class, value);
    }

    @Override
    public Node create(float value) {
        return asValue(float.class, value);
    }

    @Override
    public Node create(Float value) {
        return asValue(Float.class, value);
    }

    @Override
    public Node create(double value) {
        return asValue(double.class, value);
    }

    @Override
    public Node create(Double value) {
        return asValue(Double.class, value);
    }

    @Override
    public Node create(boolean value) {
        return asValue(boolean.class, value);
    }

    @Override
    public Node create(Boolean value) {
        return asValue(Boolean.class, value);
    }

    @Override
    public Node create(byte value) {
        return asValue(byte.class, value);
    }

    @Override
    public Node create(Byte value) {
        return asValue(Byte.class, value);
    }

    @Override
    public Node create(BigDecimal value) {
        return asValue(BigDecimal.class, value);
    }

    @Override
    public Node create(BigInteger value) {
        return asValue(BigInteger.class, value);
    }

    @Override
    public Node create(Date value) {
        return asValue(Date.class, value);
    }

    @Override
    public Node create(Enum<?> value) {
        return asValue(Enum.class, value);
    }

    @Override
    public Node create(LocalTime value) {
        return asValue(LocalTime.class, value);
    }

    @Override
    public Node create(LocalDate value) {
        return asValue(LocalDate.class, value);
    }

    @Override
    public Node create(LocalDateTime value) {
        return asValue(LocalDateTime.class, value);
    }

    @Override
    public Node create(ZonedDateTime value) {
        return asValue(ZonedDateTime.class, value);
    }

    @Override
    public Node create(Method value) {
        return asValue(Method.class, value);
    }

    @Override
    public Node create(Class<?> value) {
        return asValue(Class.class, value);
    }

    @Override
    public Node create(Field value) {
        return asValue(Field.class, value);
    }

    @Override
    public Node create(String[] value) {
        return asValue(String[].class, value);
    }

    @Override
    public Node create(CharSequence[] value) {
        return asValue(CharSequence[].class, value);
    }

    @Override
    public Node create(int[] value) {
        return asValue(int[].class, value);
    }

    @Override
    public Node create(Integer[] value) {
        return asValue(Integer[].class, value);
    }

    @Override
    public Node create(Long[] value) {
        return asValue(Long[].class, value);
    }

    @Override
    public Node create(long[] value) {
        return asValue(long[].class, value);
    }

    @Override
    public Node create(char[] value) {
        return asValue(char[].class, value);
    }

    @Override
    public Node create(Character[] value) {
        return asValue(Character[].class, value);
    }

    @Override
    public Node create(float[] value) {
        return asValue(float[].class, value);
    }

    @Override
    public Node create(Float[] value) {
        return asValue(Float[].class, value);
    }

    @Override
    public Node create(double[] value) {
        return asValue(double[].class, value);
    }

    @Override
    public Node create(Double[] value) {
        return asValue(Double[].class, value);
    }

    @Override
    public Node create(boolean[] value) {
        return asValue(boolean[].class, value);
    }

    @Override
    public Node create(Boolean[] value) {
        return asValue(Boolean[].class, value);
    }

    @Override
    public Node create(byte[] value) {
        return asValue(byte[].class, value);
    }

    @Override
    public Node create(Byte[] value) {
        return asValue(Byte[].class, value);
    }

    @Override
    public Node create(BigDecimal[] value) {
        return asValue(BigDecimal[].class, value);
    }

    @Override
    public Node create(BigInteger[] value) {
        return asValue(BigInteger[].class, value);
    }

    @Override
    public Node create(Date[] value) {
        return asValue(Date[].class, value);
    }

    @Override
    public Node create(Enum<?>[] value) {
        return asValue(Enum[].class, value);
    }

    @Override
    public Node create(LocalTime[] value) {
        return asValue(LocalTime[].class, value);
    }

    @Override
    public Node create(LocalDate[] value) {
        return asValue(LocalDate[].class, value);
    }

    @Override
    public Node create(LocalDateTime[] value) {
        return asValue(LocalDateTime[].class, value);
    }

    @Override
    public Node create(ZonedDateTime[] value) {
        return asValue(ZonedDateTime[].class, value);
    }

    @Override
    public Node create(Method[] value) {
        return asValue(Method[].class, value);
    }

    @Override
    public Node create(Class<?>[] value) {
        return asValue(Class[].class, value);
    }

    @Override
    public Node create(Field[] value) {
        return asValue(Field[].class, value);
    }

    @Override
    @SuppressWarnings({"rawtypes"})
    public <T> Node create(Collection<T> value) {
        if (value == null) {
            return create();
        }

        String desc = value.stream().filter(each -> !Commons.isKnownType(each.getClass())).map(Object::toString).reduce((acc, v) -> acc.isEmpty() ? v : acc.concat(" , ").concat(v)).orElse(null);

        if (desc != null) {
            throw new UnsupportedTypeException("value contains unsupported types such as " + desc);
        }

        Node[] container = value.stream().map(each -> asValue((Class) each.getClass(), each)).toArray(Node[]::new);

        return new SequenceNode(value.getClass(), container);
    }

    @Override
    public Node.Builder newBuilder() {
        return new NodeBuilderImpl(this);
    }


    private Node asValue(Class<?> type, Object value) {
        if (value == null) {
            return type == null ? create() : create(type);
        } else {
            return new ValueNode(value);
        }
    }

    @SuppressWarnings({"rawtypes"})
    private <T> Node asValue(Class<T[]> type, T[] value) {
        if (value == null) {
            return type == null ? create() : create(type);
        } else {
            Node[] container = Arrays.stream(value).map(each -> asValue((Class) each.getClass(), each)).toArray(Node[]::new);
            return new SequenceNode(type, container);
        }
    }

}
