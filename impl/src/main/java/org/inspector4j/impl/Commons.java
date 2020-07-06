package org.inspector4j.impl;

import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

}
