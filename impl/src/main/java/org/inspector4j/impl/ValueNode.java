package org.inspector4j.impl;


import org.inspector4j.api.Node;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.Date;
import java.util.Objects;

public class ValueNode extends SingularNode implements Node {

    private final Object value;

    public ValueNode(Object value) {
        this.value = value;
    }

    @Override
    public Class<?> getType() {
        return value.getClass();
    }

    @Override
    public boolean isNull() {
        return value == null;
    }

    @Override
    public boolean isBoolean() {
        return value instanceof Boolean;
    }

    @Override
    public boolean isArray() {
        return value.getClass().isArray();
    }

    @Override
    public boolean isDouble() {
        return value instanceof Double;
    }

    @Override
    public boolean isInteger() {
        return value instanceof Integer;
    }

    @Override
    public boolean isText() {
        return value instanceof String;
    }

    @Override
    public boolean isByte() {
        return value instanceof Byte;
    }

    @Override
    public boolean isDate() {
        return value instanceof Date;
    }

    @Override
    public boolean isLocalTime() {
        return value instanceof LocalTime;
    }

    @Override
    public boolean isLocalDate() {
        return value instanceof LocalDate;
    }

    @Override
    public boolean isLocalDateTime() {
        return value instanceof LocalDateTime;
    }

    @Override
    public boolean isZonedDateTime() {
        return value instanceof ZonedDateTime;
    }

    @Override
    public boolean isCharacter() {
        return value instanceof Character;
    }

    @Override
    public boolean isCharSequence() {
        return value instanceof CharSequence;
    }

    @Override
    public boolean isFloat() {
        return value instanceof Float;
    }

    @Override
    public boolean isBigDecimal() {
        return value instanceof BigDecimal;
    }

    @Override
    public boolean isBigInteger() {
        return value instanceof BigInteger;
    }

    @Override
    public boolean isEnumerated() {
        return value instanceof Enum;
    }

    @Override
    public boolean isLong() {
        return value instanceof Long;
    }

    @Override
    public boolean isClass() {
        return value instanceof Class;
    }

    @Override
    public boolean isMethod() {
        return value instanceof Method;
    }

    @Override
    public boolean isField() {
        return value instanceof Field;
    }

    @Override
    public double asDouble() {
        if (value instanceof Integer) {
            return (double) value;
        }

        if (!(value instanceof Double)) {
            throw new UnsupportedOperationException();
        }

        return (double) value;
    }

    @Override
    public float asFloat() {
        if (value instanceof Integer || value instanceof Double) {
            return (float) value;
        }

        if (!(value instanceof Float)) {
            throw new UnsupportedOperationException();
        }

        return (float) value;
    }

    @Override
    public byte asByte() {
        if (!(value instanceof Byte)) {
            throw new UnsupportedOperationException();
        }

        return (byte) value;
    }

    @Override
    public Date asDate() {
        if (value instanceof Date) {
            return (Date) value;
        }

        if (value instanceof LocalDate) {
            return Date.from(((LocalDate) value).atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        if (value instanceof LocalDateTime) {
            return Date.from(((LocalDateTime) value).atZone(ZoneId.systemDefault()).toInstant());
        }

        if (value instanceof ZonedDateTime) {
            return Date.from(((ZonedDateTime) value).toInstant());
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public LocalTime asLocalTime() {

        if (value instanceof LocalTime) {
            return (LocalTime) value;
        }

        if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).toLocalTime();
        }

        if (value instanceof ZonedDateTime) {
            return ((ZonedDateTime) value).toLocalTime();
        }

        if (value instanceof Date) {
            return LocalDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault()).toLocalTime();
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public LocalDate asLocalDate() {
        if (value instanceof LocalDate) {
            return (LocalDate) value;
        }

        if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).toLocalDate();
        }

        if (value instanceof ZonedDateTime) {
            return ((ZonedDateTime) value).toLocalDate();
        }

        if (value instanceof Date) {
            return ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public LocalDateTime asLocalDateTime() {
        if (value instanceof LocalDateTime) {
            return (LocalDateTime) value;
        } else if (value instanceof ZonedDateTime) {
            return ((ZonedDateTime) value).toLocalDateTime();
        } else if (value instanceof Date) {
            return ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public ZonedDateTime asZonedDateTime() {
        if (value instanceof ZonedDateTime) {
            return (ZonedDateTime) value;
        } else if (value instanceof Date) {
            return ZonedDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault());
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public char asCharacter() {
        if (value instanceof Character) {
            return (char) value;
        } else if (value instanceof CharSequence && ((CharSequence) value).length() == 1) {
            return ((CharSequence) value).charAt(0);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public CharSequence asCharSequence() {
        if (value instanceof CharSequence) {
            return (CharSequence) value;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public BigDecimal asBigDecimal() {
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value instanceof Integer) {
            return new BigDecimal((int) value);
        } else if (value instanceof Double) {
            return BigDecimal.valueOf((double) value);
        } else if (value instanceof Long) {
            return BigDecimal.valueOf((long) value);
        } else if (value instanceof Float) {
            return BigDecimal.valueOf((float) value);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public BigInteger asBigInteger() {
        if (value instanceof BigInteger) {
            return (BigInteger) value;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Enum<?> asEnum() {
        if (value instanceof Enum) {
            return (Enum<?>) value;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String asText() {
        if (value instanceof String) {
            return (String) value;
        } else if (value instanceof CharSequence || value instanceof Character) {
            return value.toString();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public long asLong() {
        if (value instanceof Long) {
            return (long) value;
        } else if (value instanceof Integer) {
            return (long) value;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean asBoolean() {
        if (value instanceof Boolean) {
            return (boolean) value;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public int asInt() {
        if (value instanceof Integer) {
            return (int) value;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> asClass() {
        if (value instanceof Class) {
            return (Class<?>) value;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Method asMethod() {
        if (value instanceof Method) {
            return (Method) value;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Field asField() {
        if (value instanceof Field) {
            return (Field) value;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueNode valueNode = (ValueNode) o;
        return Objects.equals(value, valueNode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
