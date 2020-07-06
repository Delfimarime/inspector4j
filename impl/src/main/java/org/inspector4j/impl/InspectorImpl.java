package org.inspector4j.impl;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.inspector4j.Inspector;
import org.inspector4j.api.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InspectorImpl implements Inspector {

    private final NodeFactory nodeFactory;

    public InspectorImpl() {
        this.nodeFactory = new NodeFactoryImpl();
    }

    @Override
    public Node inspect(Object object) {
        if (object instanceof Node) {
            return (Node) object;
        }
        return newChain().handle(object);
    }

    @Override
    public Analysis inspect(Method method, Object[] args) {

        if (method == null) {
            throw new IllegalArgumentException("Method mustn't be null ");
        }

        if (args == null) {
            throw new IllegalArgumentException("Args mustn't be null ");
        }

        Execution instance = new Execution();
        instance.setMethod(method);
        instance.setArgs(args);
        return (Analysis) inspect(instance);
    }

    private Chain newChain() {
        return ChainImpl.Builder.get()
                .newAction()
                    .setCondition((obj, chain) -> obj == null).setExecution((obj, chain) -> nodeFactory.create())
                .next()
                    .setCondition((obj, chain) -> obj instanceof Node).setExecution((obj, chain) -> (Node) obj)
                .next()
                    .setCondition((obj, chain) -> obj instanceof Execution).setExecution(this::toAnalysis)
                .next()
                    .setCondition((obj, chain) -> obj instanceof Boolean).setExecution((obj, chain) -> nodeFactory.create((boolean) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof Integer).setExecution((obj, chain) -> nodeFactory.create((int) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof Double).setExecution((obj, chain) -> nodeFactory.create((double) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof Long).setExecution((obj, chain) -> nodeFactory.create((long) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof Float).setExecution((obj, chain) -> nodeFactory.create((float) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof Byte).setExecution((obj, chain) -> nodeFactory.create((byte) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof BigDecimal).setExecution((obj, chain) -> nodeFactory.create((BigDecimal) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof BigInteger).setExecution((obj, chain) -> nodeFactory.create((BigInteger) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof Character).setExecution((obj, chain) -> nodeFactory.create((char) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof String).setExecution((obj, chain) -> nodeFactory.create((String) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof CharSequence).setExecution((obj, chain) -> nodeFactory.create((CharSequence) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof Enum).setExecution((obj, chain) -> nodeFactory.create((Enum<?>) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof Date).setExecution((obj, chain) -> nodeFactory.create((Date) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof LocalTime).setExecution((obj, chain) -> nodeFactory.create((LocalTime) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof LocalDate).setExecution((obj, chain) -> nodeFactory.create((LocalDate) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof LocalDateTime).setExecution((obj, chain) -> nodeFactory.create((LocalDateTime) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof ZonedDateTime).setExecution((obj, chain) -> nodeFactory.create((ZonedDateTime) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof Class).setExecution((obj, chain) -> nodeFactory.create((Class<?>) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof Method).setExecution((obj, chain) -> nodeFactory.create((Method) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof Field).setExecution((obj, chain) -> nodeFactory.create((Field) obj))
                .next()
                    .setCondition((obj, chain) -> obj instanceof Collection).setExecution((obj, chain) -> toCollection((Collection<?>) obj, chain))
                .next()
                    .setCondition((obj, chain) -> TypeUtils.isArrayType(obj.getClass())).setExecution(this::toArray)
                .next()
                    .setCondition((obj, chain) -> obj instanceof Map).setExecution(this::toMap)
                .next()
                    .setCondition((obj, chain) -> obj != null).setExecution(this::toObject)
                .apply()
                    .build();
    }

    private Node toAnalysis(Object object, Chain chain) {

        if (object == null) {
            return nodeFactory.create();
        }

        if (!(object instanceof Execution)) {
            throw new IllegalArgumentException("Unsupported type " + object.getClass().getName());
        }

        Execution instance = (Execution) object;

        AnalysisImpl.Builder builder = AnalysisImpl.Builder.get();

        for (int index = 0; index < instance.getMethod().getParameters().length; index++) {
            Parameter parameter = instance.getMethod().getParameters()[index];
            builder.set(parameter.getName(), chain.handle(instance.getArgs()[index]));
        }

        return builder.setMethod(instance.getMethod()).build();
    }

    private Node toMap(Object object, Chain chain) {
        Map<Node, Node> container = ((Map<?, ?>) object).entrySet().stream()
                .collect(Collectors.toMap(entry -> chain.handle(entry.getKey()), entry -> chain.handle(entry.getValue())));
        return ObjectNode.Builder.get().setAll(container).build();
    }

    private Node toObject(Object object, Chain chain) {
        try {

            if (object instanceof Collection) {
                return toCollection((Collection<?>) object, chain);
            } else if (object.getClass().isArray()) {
                return toArray(object, chain);
            } else if (Commons.isKnownType(object.getClass())) {
                return toBasic(object, chain);
            } else {

                Node.Builder builder = nodeFactory.newBuilder();

                for (Field field : FieldUtils.getAllFieldsList(object.getClass())) {
                    builder.setNode(field.getName(), chain.handle(FieldUtils.readField(field, object, true)));
                }

                return builder.setType(object.getClass()).build();
            }
        } catch (IllegalAccessException ex) {
            throw new InspectionException(ex);
        }
    }

    private Node toArray(Object object, Chain chain) {
        Type type = TypeUtils.getArrayComponentType(object.getClass());

        if (type.equals(int.class) || type.equals(Integer.class)) {
            return nodeFactory.create((int[]) object);
        } else if (type.equals(double.class) || type.equals(Double.class)) {
            return nodeFactory.create((double[]) object);
        } else if (type.equals(long.class) || type.equals(Long.class)) {
            return nodeFactory.create((long[]) object);
        } else if (type.equals(float.class) || type.equals(Float.class)) {
            return nodeFactory.create((float[]) object);
        } else if (type.equals(byte.class) || type.equals(Byte.class)) {
            return nodeFactory.create((byte[]) object);
        } else if (type.equals(BigDecimal.class)) {
            return nodeFactory.create((BigDecimal[]) object);
        } else if (type.equals(BigInteger.class)) {
            return nodeFactory.create((BigInteger[]) object);
        } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            return nodeFactory.create((boolean[]) object);
        } else if (type.equals(char.class) || type.equals(Character.class)) {
            return nodeFactory.create((char[]) object);
        } else if (type.equals(Date.class)) {
            return nodeFactory.create((Date[]) object);
        } else if (type.equals(LocalTime.class)) {
            return nodeFactory.create((LocalTime[]) object);
        } else if (type.equals(LocalDate.class)) {
            return nodeFactory.create((LocalDate[]) object);
        } else if (type.equals(LocalDateTime.class)) {
            return nodeFactory.create((LocalDateTime[]) object);
        } else if (type.equals(ZonedDateTime.class)) {
            return nodeFactory.create((ZonedDateTime[]) object);
        } else if (type.equals(Class.class)) {
            return nodeFactory.create((Class<?>[]) object);
        } else if (type.equals(Method.class)) {
            return nodeFactory.create((Method[]) object);
        } else if (type.equals(Field.class)) {
            return nodeFactory.create((Field[]) object);
        } else {
            return new SequenceNode(object.getClass(), stream(object).map(chain::handle).toArray(Node[]::new));
        }
    }

    private Node toCollection(Collection<?> object, Chain chain) {
        boolean isBasic = object.stream().allMatch(each -> Commons.isKnownType(each.getClass()));

        if (!isBasic) {
            return new SequenceNode(object.getClass(), object.stream().map(chain::handle).toArray(Node[]::new));
        }

        return nodeFactory.create(object);
    }

    @SuppressWarnings({"rawtypes"})
    private Node toBasic(Object object, Chain chain) {
        if (object instanceof Integer) {
            return nodeFactory.create((int) object);
        } else if (object instanceof Double) {
            return nodeFactory.create((double) object);
        } else if (object instanceof Long) {
            return nodeFactory.create((long) object);
        } else if (object instanceof Float) {
            return nodeFactory.create((float) object);
        } else if (object instanceof Byte) {
            return nodeFactory.create((byte) object);
        } else if (object instanceof BigDecimal) {
            return nodeFactory.create((BigDecimal) object);
        } else if (object instanceof BigInteger) {
            return nodeFactory.create((BigInteger) object);
        } else if (object instanceof Boolean) {
            return nodeFactory.create((boolean) object);
        } else if (object instanceof Character) {
            return nodeFactory.create((char) object);
        } else if (object instanceof Date) {
            return nodeFactory.create((Date) object);
        } else if (object instanceof LocalTime) {
            return nodeFactory.create((LocalTime) object);
        } else if (object instanceof LocalDate) {
            return nodeFactory.create((LocalDate) object);
        } else if (object instanceof LocalDateTime) {
            return nodeFactory.create((LocalDateTime) object);
        } else if (object instanceof ZonedDateTime) {
            return nodeFactory.create((ZonedDateTime) object);
        } else if (object instanceof Class) {
            return nodeFactory.create((Class) object);
        } else if (object instanceof Method) {
            return nodeFactory.create((Method) object);
        } else if (object instanceof Field) {
            return nodeFactory.create((Field) object);
        } else {
            return new SequenceNode(object.getClass(), stream(object).map(chain::handle).toArray(Node[]::new));
        }
    }

    @SuppressWarnings({"unchecked"})
    private <E extends T, T> Stream<T> stream(E object) {
        return Arrays.stream((T[]) object);
    }

}
