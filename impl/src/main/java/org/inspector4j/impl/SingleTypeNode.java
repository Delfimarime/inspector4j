package org.inspector4j.impl;

import org.inspector4j.api.Node;

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

public abstract class SingleTypeNode implements Node {

    @Override
    public Object[] keys() {
       throw new UnsupportedOperationException();
    }

    @Override
    public Node get(Node key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(Class<?> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(Field key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(Method key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(int key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(double key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(float key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(long key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(BigDecimal key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(BigInteger key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(byte key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(Date key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(LocalTime key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(LocalDate key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(LocalDateTime key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(ZonedDateTime key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(CharSequence key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(char key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(Enum<?> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node get(Object key) {
        throw new UnsupportedOperationException();
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
    public boolean isSingular() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isSequence() {
        return false;
    }

    @Override
    public boolean isCollection() {
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
    public abstract Class<?> getType();

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public boolean isInteger() {
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
    public boolean isContainer() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String asText() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long asLong() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean asBoolean() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int asInt() {
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
    public Map<Object, Object> asMap() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Object, Object> toMap() {
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

}
