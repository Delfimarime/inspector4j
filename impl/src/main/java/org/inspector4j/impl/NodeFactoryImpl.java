package org.inspector4j.impl;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.inspector4j.SecretVisibility;
import org.inspector4j.api.internal.ConversionException;
import org.inspector4j.api.internal.InspectionException;
import org.inspector4j.api.internal.Node;
import org.inspector4j.api.internal.NodeFactory;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings({"rawtypes", "unchecked"})
public class NodeFactoryImpl implements NodeFactory {

    private final UndefinedNode emptyNode;

    public NodeFactoryImpl() {
        this.emptyNode = new UndefinedNode();
    }

    @Override
    public Node create() {
        return emptyNode;
    }

    @Override
    public Node nullOf(Class<?> type) {
        return new UndefinedNode(type);
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
    public <T> Node create(Collection<T> value) {
        return create(value, null);
    }

    @Override
    public <T> Node create(Collection<T> value, SecretVisibility visibility) {
        return toCollection(visibility == null ? SecretVisibility.MASKED : visibility, value, new HashMap<>(), new HashMap<>(), new LinkedList<>());
    }

    @Override
    public <O> Node create(O value) {
        return create(value, null);
    }

    @Override
    public <O> Node create(O object, SecretVisibility visibility) {
        return create(visibility == null ? SecretVisibility.MASKED : visibility, object, new HashMap<>(), new HashMap<>(), new LinkedList<>());
    }

    private <O> Node create(SecretVisibility visibility, O object, Map<String, Node> cache, Map<String, Node> ref, Queue<Object> queue) {

        if (object == null) {
            return emptyNode;
        }

        Node instance = null;

        if (object.getClass().isArray()) {
            return toArray(visibility, object, cache, ref, queue);
        }

        if (object instanceof Collection) {
            return toCollection(visibility, object, cache, ref, queue);
        }

        if (object instanceof Node) {
            instance = (Node) object;
        }

        if (object instanceof Boolean) {
            instance = create((Boolean) object);
        }

        if (object instanceof Number) {
            instance = create((Number) object);
        }

        if (object instanceof Character) {
            instance = create((Character) object);
        }

        if (object instanceof String) {
            instance = create((String) object);
        }

        if (instance == null && object instanceof CharSequence) {
            instance = create((CharSequence) object);
        }

        if (object instanceof Enum) {
            instance = create((Enum<?>) object);
        }

        if (object instanceof Date) {
            instance = create((Date) object);
        }

        if (object instanceof Temporal) {
            instance = create((Temporal) object);
        }

        if (object instanceof AnnotatedElement) {
            instance = create((AnnotatedElement) object);
        }

        return instance != null ? instance : toNode(visibility, object, cache, ref, queue);
    }

    private <O> Node toNode(SecretVisibility visibility, O object, Map<String, Node> cache, Map<String, Node> ref, Queue<Object> queue) {
        try {

            if (object == null) {
                return null;
            }

            String key = object.getClass().getName() + "@" + object.hashCode();

            if (cache.containsKey(key)) {
                return cache.get(key);
            }

            if (queue.contains(object)) {
                Node node = (Node) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Node.class}, (obj, method, args) -> method.invoke(cache.get(key), args));
                ref.put(key, node);
                return node;
            }

            Node.Builder builder = newBuilder();

            for (Field field : FieldUtils.getAllFields(object.getClass())) {

                if (SecretVisibility.VISIBLE.equals(visibility) || (visibility.equals(SecretVisibility.MASKED))) {
                    Node node;

                    queue.add(object);

                    if (Commons.isSecret(field) && visibility.equals(SecretVisibility.MASKED)) {
                        node = new MaskedNode(field.getType());
                    } else {
                        node = create(visibility, FieldUtils.readField(field, object, true), cache, ref, queue);
                    }

                    queue.remove(object);

                    ref.remove(key);
                    cache.put(key, node);

                    builder.setNode(field.getName(), node);
                }
            }

            return builder.setType(object.getClass()).build();

        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InspectionException(ex);

        }

    }

    public Node create(Number instance) {

        if (instance instanceof Integer) {
            return create((int) instance);
        }

        if (instance instanceof Double) {
            return create((double) instance);
        }

        if (instance instanceof Long) {
            return create((Long) instance);
        }

        if (instance instanceof Float) {
            return create((Float) instance);
        }

        if (instance instanceof Byte) {
            return create((Byte) instance);
        }

        if (instance instanceof BigInteger) {
            return create((BigInteger) instance);
        }

        if (instance instanceof BigDecimal) {
            return create((BigDecimal) instance);
        }

        return null;
    }

    public Node create(Temporal instance) {

        if (instance instanceof LocalTime) {
            return create((LocalTime) instance);
        }

        if (instance instanceof LocalDateTime) {
            return create((LocalDateTime) instance);
        }

        if (instance instanceof ZonedDateTime) {
            return create((ZonedDateTime) instance);
        }

        return null;
    }

    public Node create(AnnotatedElement instance) {

        if (instance instanceof Class) {
            return create((Class<?>) instance);
        }

        if (instance instanceof Method) {
            return create((Method) instance);
        }

        if (instance instanceof Field) {
            return create((Field) instance);
        }

        return null;
    }

    @Override
    public Node.Builder newBuilder() {
        return new NodeBuilderImpl(this);
    }

    private Node asValue(Class<?> type, Object value) {
        if (value == null) {
            return type == null ? create() : create(type);
        } else {
            return new BasicTypeNode(value);
        }
    }

    private <T> Node asValue(Class<T[]> type, T[] value) {
        if (value == null) {
            return type == null ? create() : create(type);
        } else {
            Node[] container = Arrays.stream(value).map(each -> asValue(each.getClass(), each)).toArray(Node[]::new);
            return new IterableNode(type, container);
        }
    }

    private Node toArray(SecretVisibility visibility, Object object, Map<String, Node> cache, Map<String, Node> ref, Queue<Object> queue) {
        if (object == null) {
            return null;
        }

        Function<Object, Node> mapper = null;
        Class<?> objectType = (Class<?>) TypeUtils.getArrayComponentType(object.getClass());

        if (objectType.isArray()) {
            mapper = (obj) -> toArray(visibility, obj, cache, ref, queue);
        }

        if (ClassUtils.isAssignable(objectType, Collection.class)) {
            mapper = (obj) -> toCollection(visibility, obj, cache, ref, queue);
        }

        if (ClassUtils.isAssignable(objectType, Node.class)) {
            mapper = (obj) -> (Node) obj;
        }

        if (ClassUtils.isAssignable(objectType, Boolean.class)) {
            mapper = (obj) -> create((boolean) obj);
        }

        if (ClassUtils.isAssignable(objectType, Number.class)) {
            mapper = (obj) -> create((Number) obj);
        }

        if (ClassUtils.isAssignable(objectType, Character.class)) {
            mapper = (obj) -> create((Character) obj);
        }

        if (ClassUtils.isAssignable(objectType, String.class)) {
            mapper = (obj) -> create((String) obj);
        }

        if (mapper == null && ClassUtils.isAssignable(objectType, CharSequence.class)) {
            mapper = (obj) -> create((CharSequence) obj);
        }

        if (ClassUtils.isAssignable(objectType, Enum.class)) {
            mapper = (obj) -> create((Enum<?>) obj);
        }

        if (ClassUtils.isAssignable(objectType, Date.class)) {
            mapper = (obj) -> create((Date) obj);
        }

        if (ClassUtils.isAssignable(objectType, Temporal.class)) {
            mapper = (obj) -> create((Temporal) obj);
        }

        if (ClassUtils.isAssignable(objectType, AnnotatedElement.class)) {
            mapper = (obj) -> create((AnnotatedElement) obj);
        }

        if (mapper == null) {
            mapper = (obj) -> toNode(visibility, obj, cache, ref, queue);
        }

        return new IterableNode(objectType, stream(object).map(mapper).toArray(Node[]::new));
    }

    private Node toCollection(SecretVisibility visibility, Object object, Map<String, Node> cache, Map<String, Node> ref, Queue<Object> queue) {
        if (object == null) {
            return create();
        }

        String desc = ((Collection<?>) object).stream().filter(each -> !Commons.isKnownType(each.getClass())).map(Object::toString).reduce((acc, v) -> acc.isEmpty() ? v : acc.concat(" , ").concat(v)).orElse(null);

        if (desc != null) {
            throw new ConversionException("value contains unsupported types such as " + desc);
        }

        Node[] container = (Node[]) ((Collection) object).stream().map(each -> create(visibility, each, cache, ref, queue)).toArray(Node[]::new);

        return new IterableNode(object.getClass(), container);
    }

    private <E extends T, T> Stream<T> stream(E object) {
        return Arrays.stream((T[]) object);
    }

}
