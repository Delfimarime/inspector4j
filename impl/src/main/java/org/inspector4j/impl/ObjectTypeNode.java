package org.inspector4j.impl;

import org.inspector4j.api.internal.Node;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.function.BiPredicate;

public abstract class ObjectTypeNode implements Node {

    protected abstract Map<Node,Node>getMap();

    @Override
    public Object[] keys() {
        return getMap().keySet().toArray();
    }

    @Override
    public Node get(Node key) {
        return get((Object) key);
    }

    @Override
    public Node get(int key) {
        return get((Object) key);
    }

    @Override
    public Node get(double key) {
        return get((Object) key);
    }

    @Override
    public Node get(float key) {
        return get((Object) key);
    }

    @Override
    public Node get(long key) {
        return get((Object) key);
    }

    @Override
    public Node get(BigDecimal key) {
        return get((Object) key);
    }

    @Override
    public Node get(BigInteger key) {
        return get((Object) key);
    }

    @Override
    public Node get(byte key) {
        return get((Object) key);
    }

    @Override
    public Node get(Date key) {
        return get((Object) key);
    }

    @Override
    public Node get(LocalTime key) {
        return get((Object) key);
    }

    @Override
    public Node get(LocalDate key) {
        return get((Object) key);
    }

    @Override
    public Node get(LocalDateTime key) {
        return get((Object) key);
    }

    @Override
    public Node get(ZonedDateTime key) {
        return get((Object) key);
    }

    @Override
    public Node get(CharSequence key) {
        return get((Object) key);
    }

    @Override
    public Node get(char key) {
        return get((Object) key);
    }

    @Override
    public Node get(Enum<?> key) {
        return get((Object) key);
    }

    @Override
    public Node get(String key) {
        return get((Object) key);
    }

    @Override
    public Node get(Class<?> key) {
        return get((Object) key);
    }

    @Override
    public Node get(Field key) {
        return get((Object) key);
    }

    @Override
    public Node get(Method key) {
        return get((Object) key);
    }

    @Override
    public Node get(Object key) {
        if (key instanceof Node && ((Node) key).isContainer()) {
            return getMap().get(key);
        } else if (key instanceof String) {
            return find((k, v) -> k.isText() && k.asText().equals(key));
        } else if (key instanceof Enum<?>) {
            return find((k, v) -> k.isEnumerated() && k.asEnum().equals(key));
        } else if (key instanceof Character) {
            return find((k, v) -> k.isCharacter() && k.asCharacter() == (char) key);
        } else if (key instanceof CharSequence) {
            return find((k, v) -> k.isCharSequence() && k.asCharSequence().equals(key));
        } else if (key instanceof ZonedDateTime) {
            return find((k, v) -> k.isZonedDateTime() && k.asZonedDateTime().equals(key));
        } else if (key instanceof LocalDateTime) {
            return find((k, v) -> k.isLocalDateTime() && k.asLocalDateTime().equals(key));
        } else if (key instanceof LocalDate) {
            return find((k, v) -> k.isLocalDate() && k.asLocalDate().equals(key));
        } else if (key instanceof LocalTime) {
            return find((k, v) -> k.isLocalTime() && k.asLocalTime().equals(key));
        } else if (key instanceof Date) {
            return find((k, v) -> k.isDate() && k.asDate().equals(key));
        } else if (key instanceof Byte) {
            return find((k, v) -> k.isByte() && k.asByte() == (byte) key);
        } else if (key instanceof BigDecimal) {
            return find((k, v) -> k.isBigDecimal() && k.asBigDecimal().equals(key));
        } else if (key instanceof BigInteger) {
            return find((k, v) -> k.isBigInteger() && k.asBigInteger().equals(key));
        } else if (key instanceof Long) {
            return find((k, v) -> k.isLong() && k.asLong() == (long) key);
        } else if (key instanceof Float) {
            return find((k, v) -> k.isFloat() && k.asFloat() == (float) key);
        } else if (key instanceof Integer) {
            return find((k, v) -> k.isInteger() && k.asInt() == (float) key);
        } else if (key instanceof Class) {
            return find((k, v) -> k.isClass() && k.asClass().equals(key));
        } else if (key instanceof Field) {
            return find((k, v) -> k.isField() && k.asField().equals(key));
        } else if (key instanceof Method) {
            return find((k, v) -> k.isMethod() && k.asMethod().equals(key));
        } else if (key instanceof Node) {
            return find((k, v) -> k.equals(key));
        } else {
            return null;
        }
    }

    @Override
    public boolean isSingular() {
        return false;
    }

    @Override
    public boolean isContainer() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isSequence() {
        return false;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public boolean isLong() {
        return false;
    }

    @Override
    public boolean isText() {
        return false;
    }

    @Override
    public boolean isInteger() {
        return false;
    }

    @Override
    public boolean isByte() {
        return false;
    }

    @Override
    public boolean isDate() {
        return false;
    }

    @Override
    public boolean isLocalTime() {
        return false;
    }

    @Override
    public boolean isLocalDate() {
        return false;
    }

    @Override
    public boolean isLocalDateTime() {
        return false;
    }

    @Override
    public boolean isZonedDateTime() {
        return false;
    }

    @Override
    public boolean isCharacter() {
        return false;
    }

    @Override
    public boolean isCharSequence() {
        return false;
    }

    @Override
    public boolean isFloat() {
        return false;
    }

    @Override
    public boolean isBigDecimal() {
        return false;
    }

    @Override
    public boolean isBigInteger() {
        return false;
    }

    @Override
    public boolean isEnumerated() {
        return false;
    }

    @Override
    public boolean isClass() {
        return false;
    }

    @Override
    public boolean isMethod() {
        return false;
    }

    @Override
    public boolean isField() {
        return false;
    }

    @Override
    public int size() {
        return getMap().size();
    }

    @Override
    public int asInt() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long asLong() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String asText() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean asBoolean() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double asDouble() {
        throw new UnsupportedOperationException();
    }

    @Override
    public float asFloat() {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte asByte() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Date asDate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LocalTime asLocalTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LocalDate asLocalDate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LocalDateTime asLocalDateTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ZonedDateTime asZonedDateTime() {
        throw new UnsupportedOperationException();
    }

    @Override
    public char asCharacter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CharSequence asCharSequence() {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal asBigDecimal() {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigInteger asBigInteger() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Enum<?> asEnum() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> asClass() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Method asMethod() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Field asField() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] asArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public abstract Class<?> getType();

    private Node find(BiPredicate<Node, Node> condition) {
        return getMap().entrySet().stream().filter(each -> condition.test(each.getKey(), each.getValue())).map(Map.Entry::getValue).findFirst().orElse(null);
    }

}
