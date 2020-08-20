package org.inspector4j.core;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.inspector4j.Inspector;
import org.inspector4j.api.*;
import org.inspector4j.impl.IterableNode;
import org.inspector4j.impl.NodeFactoryImpl;
import org.inspector4j.impl.NodeImpl;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultInspector implements Inspector {

    private List<Handler> seq;
    private final NodeFactory factory;
    private final ThreadLocal<Boolean> inspectAll;

    public DefaultInspector() {
        this.initialize();
        this.factory = new NodeFactoryImpl();
        this.inspectAll = new ThreadLocal<>();
    }

    @Override
    public InspectionResult inspect(Method method, Object[] args) {
        return inspect(method, args, Boolean.FALSE);
    }

    @Override
    public InspectionResult inspect(Method method, Object[] args, boolean inspectAll) {
        try {

            this.inspectAll.set(determineInspectAll(inspectAll));

            if (method == null) {
                throw new IllegalArgumentException("Method mustn't be null ");
            }

            if (args == null) {
                throw new IllegalArgumentException("Args mustn't be null ");
            }

            return getInstance(getBuilder(method, args).setType(InspectionResult.class).build());
        } finally {
            this.inspectAll.remove();
        }
    }

    private Node.Builder getBuilder(Method method, Object[] args) {
        Node.Builder builder = factory.newBuilder();

        List<Handler> seq = new ArrayList<>(this.seq);
        seq.add(3, toReference());
        DefaultHandlingContext.Builder.Factory factory = new DefaultHandlingContext.Builder.Factory(seq);

        for (int index = 0; index < method.getParameterCount(); index++) {

            Parameter parameter = method.getParameters()[index];

            if (isCandidate(parameter)) {
                builder.setNode(parameter.getName(), inspect(factory, args[index]));
            }

        }
        return builder;
    }

    private InspectionResult getInstance(Node node) {
        return (InspectionResult) Proxy.newProxyInstance(InspectionResult.class.getClassLoader(), new Class[]{Node.class, InspectionResult.class}, (obj, exec, vars) -> {
            if (exec.getName().equals("getMethod")) {
                return exec;
            } else if (exec.getName().equals("getArgs")) {
                return Arrays.stream(node.keys()).map(node::get).toArray(Node[]::new);
            } else {
                return MethodUtils.invokeMethod(node, exec.getName(), vars);
            }
        });
    }

    private boolean isCandidate(AnnotatedElement each) {

        if (!inspectAll.get()) {
            return true;
        }

        if (each.isAnnotationPresent(Secret.class)) {
            return Boolean.FALSE;
        }

        if (each instanceof Field) {
            return !((Field) each).getType().isAnnotationPresent(Secret.class);
        }

        if (each instanceof Parameter) {
            return !((Parameter) each).getType().isAnnotationPresent(Secret.class);
        }

        throw new IllegalArgumentException("Illegal element type:" + each);
    }

    private void initialize() {
        if (seq != null) {
            return;
        }

        this.seq = new ArrayList<>();
        seq.add(from((context, obj) -> obj == null, (context, object) -> factory.create()));
        seq.add(from((context, obj) -> context.get(obj) != null, HandlingContext::get));
        seq.add(from((context, obj) -> obj instanceof Node, (context, object) -> (Node) object));
        seq.add(from((context, obj) -> obj instanceof Boolean, (context, object) -> factory.create((boolean) object)));
        seq.add(from((context, obj) -> obj instanceof Integer, (context, object) -> factory.create((int) object)));
        seq.add(from((context, obj) -> obj instanceof Double, (context, object) -> factory.create((double) object)));
        seq.add(from((context, obj) -> obj instanceof Long, (context, object) -> factory.create((long) object)));
        seq.add(from((context, obj) -> obj instanceof Float, (context, object) -> factory.create((float) object)));
        seq.add(from((context, obj) -> obj instanceof Byte, (context, object) -> factory.create((byte) object)));
        seq.add(from((context, obj) -> obj instanceof BigInteger, (context, object) -> factory.create((BigInteger) object)));
        seq.add(from((context, obj) -> obj instanceof BigDecimal, (context, object) -> factory.create((BigDecimal) object)));
        seq.add(from((context, obj) -> obj instanceof Character, (context, object) -> factory.create((char) object)));
        seq.add(from((context, obj) -> obj instanceof String, (context, object) -> factory.create((String) object)));
        seq.add(from((context, obj) -> obj instanceof CharSequence, (context, object) -> factory.create((CharSequence) object)));
        seq.add(from((context, obj) -> obj instanceof Enum, (context, object) -> factory.create((Enum<?>) object)));
        seq.add(from((context, obj) -> obj instanceof Date, (context, object) -> factory.create((Date) object)));
        seq.add(from((context, obj) -> obj instanceof LocalTime, (context, object) -> factory.create((LocalTime) object)));
        seq.add(from((context, obj) -> obj instanceof LocalDate, (context, object) -> factory.create((LocalDate) object)));
        seq.add(from((context, obj) -> obj instanceof LocalDateTime, (context, object) -> factory.create((LocalDateTime) object)));
        seq.add(from((context, obj) -> obj instanceof ZonedDateTime, (context, object) -> factory.create((ZonedDateTime) object)));
        seq.add(from((context, obj) -> obj instanceof Class, (context, object) -> factory.create((Class<?>) object)));
        seq.add(from((context, obj) -> obj instanceof Method, (context, object) -> factory.create((Method) object)));
        seq.add(from((context, obj) -> obj instanceof Field, (context, object) -> factory.create((Field) object)));
        seq.add(from((context, obj) -> obj instanceof Collection, (context, object) -> toCollection(context, (Collection<?>) object)));
        seq.add(from((context, obj) -> TypeUtils.isArrayType(obj.getClass()), this::toArray));
        seq.add(from((context, obj) -> obj instanceof Map, this::toMap));
        seq.add(from((context, obj) -> obj != null, this::toObject));
    }

    private Node inspect(HandlingContext.Builder.Factory factory, Object arg) {

        if (arg instanceof Node) {
            return (Node) arg;
        }

        return factory.get().setObject(arg).build().proceed();

    }

    private Handler toReference() {
        List<String> keys = new ArrayList<>();
        Map<String, Node> referenceMap = new HashMap<>();

        return (context, object) -> {

            if (object == null) {
                return context.proceed();
            }

            String key = object.getClass().getName() + "@" + object.hashCode();

            if (keys.contains(key)) {
                Node node = referenceMap.get(key);

                if (node == null) {
                    node = (Node) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Node.class}, (obj, method, args) -> method.invoke(context.get(object), args));
                    referenceMap.put(key, node);
                    return node;
                }
            }

            keys.add(key);
            Node node = context.proceed();

            keys.remove(key);
            referenceMap.remove(key);

            return node;
        };
    }

    private Node toMap(HandlingContext context, Object object) {
        Map<Node, Node> container = ((Map<?, ?>) object).entrySet().stream().collect(Collectors.toMap(entry -> context.getFactory().get().setObject(entry.getKey()).build().proceed(), entry -> context.getFactory().get().setObject(entry.getValue()).build().proceed()));
        return NodeImpl.Builder.get().setAll(container).build();
    }

    @SuppressWarnings({"rawtypes"})
    private Node toBasic(HandlingContext context, Object object) {
        if (object instanceof Integer) {
            return factory.create((int) object);
        } else if (object instanceof Double) {
            return factory.create((double) object);
        } else if (object instanceof Long) {
            return factory.create((long) object);
        } else if (object instanceof Float) {
            return factory.create((float) object);
        } else if (object instanceof Byte) {
            return factory.create((byte) object);
        } else if (object instanceof BigDecimal) {
            return factory.create((BigDecimal) object);
        } else if (object instanceof BigInteger) {
            return factory.create((BigInteger) object);
        } else if (object instanceof Boolean) {
            return factory.create((boolean) object);
        } else if (object instanceof Character) {
            return factory.create((char) object);
        } else if (object instanceof Date) {
            return factory.create((Date) object);
        } else if (object instanceof LocalTime) {
            return factory.create((LocalTime) object);
        } else if (object instanceof LocalDate) {
            return factory.create((LocalDate) object);
        } else if (object instanceof LocalDateTime) {
            return factory.create((LocalDateTime) object);
        } else if (object instanceof ZonedDateTime) {
            return factory.create((ZonedDateTime) object);
        } else if (object instanceof Class) {
            return factory.create((Class) object);
        } else if (object instanceof Method) {
            return factory.create((Method) object);
        } else if (object instanceof Field) {
            return factory.create((Field) object);
        } else {
            return new IterableNode(object.getClass(), stream(object).map(each -> toBasic(context, each)).toArray(Node[]::new));
        }
    }

    private Node toObject(HandlingContext context, Object object) {
        try {

            if (object instanceof Collection) {
                return toCollection(context, (Collection<?>) object);
            } else if (object.getClass().isArray()) {
                return toArray(context, object);
            } else if (Commons.isKnownType(object.getClass())) {
                return toBasic(context, object);
            } else {
                Node.Builder builder = factory.newBuilder();

                for (Field field : FieldUtils.getAllFieldsList(object.getClass())) {
                    if (isCandidate(field)) {
                        Object value = FieldUtils.readField(field, object, true);
                        builder.setNode(field.getName(), context.getFactory().get().setObject(value).build().proceed());
                    }
                }

                return builder.setType(object.getClass()).build();
            }
        } catch (IllegalAccessException ex) {
            throw new InspectionException(ex);
        }
    }

    private Node toArray(HandlingContext context, Object object) {
        Type type = TypeUtils.getArrayComponentType(object.getClass());

        if (type.equals(int.class) || type.equals(Integer.class)) {
            return factory.create((int[]) object);
        } else if (type.equals(double.class) || type.equals(Double.class)) {
            return factory.create((double[]) object);
        } else if (type.equals(long.class) || type.equals(Long.class)) {
            return factory.create((long[]) object);
        } else if (type.equals(float.class) || type.equals(Float.class)) {
            return factory.create((float[]) object);
        } else if (type.equals(byte.class) || type.equals(Byte.class)) {
            return factory.create((byte[]) object);
        } else if (type.equals(BigDecimal.class)) {
            return factory.create((BigDecimal[]) object);
        } else if (type.equals(BigInteger.class)) {
            return factory.create((BigInteger[]) object);
        } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            return factory.create((boolean[]) object);
        } else if (type.equals(char.class) || type.equals(Character.class)) {
            return factory.create((char[]) object);
        } else if (type.equals(Date.class)) {
            return factory.create((Date[]) object);
        } else if (type.equals(LocalTime.class)) {
            return factory.create((LocalTime[]) object);
        } else if (type.equals(LocalDate.class)) {
            return factory.create((LocalDate[]) object);
        } else if (type.equals(LocalDateTime.class)) {
            return factory.create((LocalDateTime[]) object);
        } else if (type.equals(ZonedDateTime.class)) {
            return factory.create((ZonedDateTime[]) object);
        } else if (type.equals(Class.class)) {
            return factory.create((Class<?>[]) object);
        } else if (type.equals(Method.class)) {
            return factory.create((Method[]) object);
        } else if (type.equals(Field.class)) {
            return factory.create((Field[]) object);
        } else {
            return new IterableNode(object.getClass(), stream(object).map(each -> context.getFactory().get().setObject(each).build().proceed()).toArray(Node[]::new));
        }
    }

    private Node toCollection(HandlingContext context, Collection<?> object) {
        boolean isBasic = object.stream().allMatch(each -> Commons.isKnownType(each.getClass()));

        if (!isBasic) {
            return new IterableNode(object.getClass(), object.stream().map(each -> context.getFactory().get().setObject(each).build().proceed()).toArray(Node[]::new));
        }

        return factory.create(object);
    }

    private Handler from(BiPredicate<HandlingContext, Object> condition, BiFunction<HandlingContext, Object, Node> execution) {
        return (context, object) -> {
            boolean accept = true;

            if (condition != null) {
                accept = condition.test(context, object);
            }

            if (accept && execution != null) {
                return execution.apply(context, object);
            }

            return context.proceed();
        };
    }

    @SuppressWarnings({"unchecked"})
    private <E extends T, T> Stream<T> stream(E object) {
        return Arrays.stream((T[]) object);
    }

    private boolean isSecretOverrideAllowed() {
        return Optional.ofNullable(System.getProperty("org.inspector4j.secrets.visibility.override")).map(Boolean::parseBoolean).orElse(false);
    }

    private boolean determineInspectAll(boolean inspectAll) {
        if (!isSecretOverrideAllowed()) {
            return Optional.ofNullable(System.getProperty("org.inspector4j.secrets.is-aware")).map(Boolean::parseBoolean).orElse(false);
        }
        return inspectAll;
    }

}
