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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;

public class ObjectNode implements Node {

    private Class<?> type;
    private Map<Node, Node> map;

    private ObjectNode() {
    }

    @Override
    public Object[] keys() {
        return map.keySet().stream().toArray(Object[]::new);
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
            return map.get(key);
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
        return map.size();
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
    public Class<?> getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectNode that = (ObjectNode) o;
        return Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }

    @Override
    public String toString() {
        return "{ [" + map.entrySet().stream().map(each -> each.getKey() + " = " + each.getValue()).reduce((acc, v) -> acc.isEmpty() ? v : acc.concat(" , ").concat(v)).orElse("") + "]}";
    }

    private Node find(BiPredicate<Node, Node> condition) {
        return map.entrySet().stream().filter(each -> condition.test(each.getKey(), each.getValue())).map(Map.Entry::getValue).findFirst().orElse(null);
    }

    public static class Builder {

        private Class<?> type;
        private final Map<Node, Node> map;

        private Builder() {
            this.map = new HashMap<>();
        }

        public static Builder get() {
            return new Builder();
        }

        public Builder setType(Class<?> type) {
            if (type != null) {
                this.type = type;
            }
            return this;
        }

        public Builder set(String name, Node node) {
            if (name != null && node != null) {
                set(new ValueNode(name), node);
            }
            return this;
        }

        public Builder set(Node key, Node node) {
            if (key != null && node != null) {
                this.map.put(key, node);
            }
            return this;
        }

        public Builder setAll(Map<Node, Node> map) {
            if (map != null) {
                this.map.putAll(map);
            }
            return this;
        }

        public Node build() {
            ObjectNode instance = new ObjectNode();

            if (type == null) {
                throw new IllegalArgumentException("Type mustn't be null");
            }

            if (map.isEmpty()) {
                throw new IllegalArgumentException("ObjectNode must have at least one field");
            }

            instance.type = type;
            instance.map = map;
            return instance;
        }

    }

}
