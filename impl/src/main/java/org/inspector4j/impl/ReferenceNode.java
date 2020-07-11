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
import java.util.function.Supplier;

public class ReferenceNode implements Node {

    private final Supplier<Node> loader;

    public ReferenceNode(Supplier<Node> loader) {
        this.loader = loader;
    }

    @Override
    public Object[] keys() {
        return loader.get().keys();
    }

    @Override
    public Node get(Node key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(int key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(double key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(float key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(long key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(BigDecimal key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(BigInteger key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(byte key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(Date key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(LocalTime key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(LocalDate key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(LocalDateTime key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(ZonedDateTime key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(CharSequence key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(char key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(Enum<?> key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(String key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(Object key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(Class<?> key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(Field key) {
        return loader.get().get(key);
    }

    @Override
    public Node get(Method key) {
        return loader.get().get(key);
    }

    @Override
    public boolean isSingular() {
        return loader.get().isSingular();
    }

    @Override
    public boolean isClass() {
        return loader.get().isClass();
    }

    @Override
    public boolean isMethod() {
        return loader.get().isMethod();
    }

    @Override
    public boolean isField() {
        return loader.get().isField();
    }

    @Override
    public boolean isContainer() {
        return loader.get().isContainer();
    }

    @Override
    public boolean isSequence() {
        return loader.get().isSequence();
    }

    @Override
    public boolean isNull() {
        return loader.get().isNull();
    }

    @Override
    public boolean isArray() {
        return loader.get().isArray();
    }

    @Override
    public boolean isCollection() {
        return loader.get().isCollection();
    }

    @Override
    public boolean isDouble() {
        return loader.get().isDouble();
    }

    @Override
    public boolean isBoolean() {
        return loader.get().isBoolean();
    }

    @Override
    public boolean isLong() {
        return loader.get().isLong();
    }

    @Override
    public boolean isText() {
        return loader.get().isText();
    }

    @Override
    public boolean isInteger() {
        return loader.get().isInteger();
    }

    @Override
    public boolean isByte() {
        return loader.get().isByte();
    }

    @Override
    public boolean isDate() {
        return loader.get().isDate();
    }

    @Override
    public boolean isLocalTime() {
        return loader.get().isLocalTime();
    }

    @Override
    public boolean isLocalDate() {
        return loader.get().isLocalDate();
    }

    @Override
    public boolean isLocalDateTime() {
        return loader.get().isLocalDateTime();
    }

    @Override
    public boolean isZonedDateTime() {
        return loader.get().isZonedDateTime();
    }

    @Override
    public boolean isCharacter() {
        return loader.get().isCharacter();
    }

    @Override
    public boolean isCharSequence() {
        return loader.get().isCharSequence();
    }

    @Override
    public boolean isFloat() {
        return loader.get().isFloat();
    }

    @Override
    public boolean isBigDecimal() {
        return loader.get().isBigDecimal();
    }

    @Override
    public boolean isBigInteger() {
        return loader.get().isBigInteger();
    }

    @Override
    public boolean isEnumerated() {
        return loader.get().isEnumerated();
    }

    @Override
    public int size() {
        return loader.get().size();
    }

    @Override
    public int asInt() {
        return loader.get().asInt();
    }

    @Override
    public long asLong() {
        return loader.get().asLong();
    }

    @Override
    public String asText() {
        return loader.get().asText();
    }

    @Override
    public boolean asBoolean() {
        return loader.get().asBoolean();
    }

    @Override
    public double asDouble() {
        return loader.get().asDouble();
    }

    @Override
    public float asFloat() {
        return loader.get().asFloat();
    }

    @Override
    public byte asByte() {
        return loader.get().asByte();
    }

    @Override
    public Date asDate() {
        return loader.get().asDate();
    }

    @Override
    public LocalTime asLocalTime() {
        return loader.get().asLocalTime();
    }

    @Override
    public LocalDate asLocalDate() {
        return loader.get().asLocalDate();
    }

    @Override
    public LocalDateTime asLocalDateTime() {
        return loader.get().asLocalDateTime();
    }

    @Override
    public ZonedDateTime asZonedDateTime() {
        return loader.get().asZonedDateTime();
    }

    @Override
    public char asCharacter() {
        return loader.get().asCharacter();
    }

    @Override
    public CharSequence asCharSequence() {
        return loader.get().asCharSequence();
    }

    @Override
    public BigDecimal asBigDecimal() {
        return loader.get().asBigDecimal();
    }

    @Override
    public BigInteger asBigInteger() {
        return loader.get().asBigInteger();
    }

    @Override
    public Enum<?> asEnum() {
        return loader.get().asEnum();
    }

    @Override
    public Class<?> asClass() {
        return loader.get().asClass();
    }

    @Override
    public Method asMethod() {
        return loader.get().asMethod();
    }

    @Override
    public Field asField() {
        return loader.get().asField();
    }

    @Override
    public Map<Object, Object> asMap() {
        return toMap();
    }

    @Override
    public Map<Object, Object> toMap() {
        return loader.get().toMap();
    }

    @Override
    public Class<?> getType() {
        return loader.get().getType();
    }

}
