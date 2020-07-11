package org.inspector4j.impl;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.inspector4j.api.Node;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Commons {

    private static final Class<?>[] KNOWN_TYPES = {int.class, Integer.class, float.class, Float.class,
            long.class, Long.class, byte.class, double.class, Double.class, byte.class, Byte.class,
            char.class, Character.class, CharSequence.class, boolean.class, Boolean.class,
            BigDecimal.class, BigInteger.class, Date.class, LocalTime.class, LocalDate.class,
            LocalDateTime.class, ZonedDateTime.class, Method.class, Class.class, Field.class};

    public static boolean isKnownType(Type type) {
        if (type == null) {
            return Boolean.FALSE;
        } else if (TypeUtils.isAssignable(type, Enum.class)) {
            return Boolean.TRUE;
        } else if (TypeUtils.isArrayType(type)) {
            return isKnownType(TypeUtils.getArrayComponentType(type));
        } else if (TypeUtils.isAssignable(type, Collection.class)) {
            return isKnownType(TypeUtils.getRawType(type, Collection.class));
        } else {
            return isEqualAny(type, KNOWN_TYPES);
        }
    }

    public static boolean isEqualAny(Type cls, Type... anyOf) {
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

    public static Object unwrap(Object x) {
        if (!(x instanceof Node)) {
            return x;
        } else {
            return unwrap((Node) x);
        }
    }

    public static Object unwrap(Node node) {
        if (node == null || node.isNull()) {
            return null;
        } else if (node.isArray()) {
            return Arrays.stream(node.keys()).map(Commons::unwrap).map(node::get).map(Commons::unwrap).toArray(Object[]::new);
        } else if (node.isCollection()) {
            return Arrays.stream(node.keys()).map(Commons::unwrap).collect(Collectors.toList());
        } else if (node.isContainer()) {
            return asMap(node);
        } else if (node.isSingular()) {
            return extract(node);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private static Object asMap(Node node) {
        Map<Object, Object> map = new HashMap<>();
        for (Object key : node.keys()) {
            map.put(Commons.unwrap(key), Commons.unwrap(node.get(key)));
        }
        return map;
    }

    private static Object extract(Node node) {
        if (node == null || node.isNull()) {
            return null;
        } else if (node.isByte()) {
            return node.asByte();
        } else if (node.isInteger()) {
            return node.asInt();
        } else if (node.isDouble()) {
            return node.asDouble();
        } else if (node.isFloat()) {
            return node.asFloat();
        } else if (node.isLong()) {
            return node.asLong();
        } else if (node.isBigInteger()) {
            return node.asBigInteger();
        } else if (node.isBigDecimal()) {
            return node.asBigDecimal();
        } else if (node.isBoolean()) {
            return node.asBoolean();
        } else if (node.isText()) {
            return node.asText();
        } else if (node.isDate()) {
            return node.asDate();
        } else if (node.isLocalTime()) {
            return node.asLocalTime();
        } else if (node.isLocalDate()) {
            return node.asLocalDate();
        } else if (node.isLocalDateTime()) {
            return node.asLocalDateTime();
        } else if (node.isZonedDateTime()) {
            return node.asZonedDateTime();
        } else if (node.isCharacter()) {
            return node.asCharacter();
        } else if (node.isText()) {
            return node.asText();
        } else if (node.isCharSequence()) {
            return node.asCharSequence();
        } else if (node.isEnumerated()) {
            return node.asEnum();
        } else if (node.isClass()) {
            return node.asClass();
        } else if (node.isField()) {
            return node.asField();
        } else if (node.isMethod()) {
            return node.asMethod();
        } else {
            throw new UnsupportedOperationException("Node doesn't support toMap");
        }
    }

}
