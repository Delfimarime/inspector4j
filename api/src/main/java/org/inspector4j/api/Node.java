package org.inspector4j.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

/**
 * Tree Node representation of a Java Object
 */
public interface Node {

    /**
     * Returns the keys used to access values on this node
     *
     * @return keys of the node which may be of type {@link Node} or {@link String} or {@link Integer}
     */
    Object[] keys();

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(Node key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link Integer}
     * or for nodes where {@link #isArray()} is TRUE   or Collections {@link #isCollection()} is TRUE
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(int key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link Double}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(double key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link Float}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(float key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link Long}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(long key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link BigDecimal}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(BigDecimal key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link BigInteger}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(BigInteger key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link Byte}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(byte key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link java.util.Date}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(Date key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link LocalTime}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(LocalTime key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link LocalDate}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(LocalDate key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link LocalDateTime}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(LocalDateTime key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link ZonedDateTime}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(ZonedDateTime key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link CharSequence}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(CharSequence key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link Character}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(char key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link Enum}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(Enum<?> key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link String}
     * or another Java Type Object where the key matches a field within the object
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(String key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and this method acts a proxy method where it calls the most suitable method
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(Object key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link Class}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(Class<?> key);

    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link Field}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(Field key);


    /**
     * Returns a  sub-node within this node which key is equal to the key argument
     * This method may throw {@link UnsupportedOperationException} in case {@link #isSingular()}} is TRUE
     * and its intended to be use on a Node that represents {@link java.util.Map } where the key type is {@link Method}
     *
     * @param key which identifies the sub-node
     * @return Sub-node which match the key
     */
    Node get(Method key);

    /**
     * Returns TRUE in case this node represents a Scalar
     *
     * @return TRUE if scalar otherwise FALSE
     */
    boolean isSingular();

    /**
     * Returns TRUE in case this node represents a {@link Class}
     *
     * @return TRUE if Class node
     */
    boolean isClass();

    /**
     * Returns TRUE in case this node represents a {@link Method}
     *
     * @return TRUE if Class node
     */
    boolean isMethod();


    /**
     * Returns TRUE in case this node represents a {@link Field}
     *
     * @return TRUE if Class node
     */
    boolean isField();

    /**
     * Returns TRUE in case this node represents a non-scalar Object or a Map nor a Collection nor Array
     *
     * @return TRUE if container node
     */
    boolean isContainer();

    /**
     * Returns TRUE in case this node represents an Collection or Array
     *
     * @return TRUE if Collection or Array otherwise FALSE
     */
    boolean isSequence();

    /**
     * Returns TRUE in case this node represents null value
     *
     * @return TRUE if null
     */
    boolean isNull();

    /**
     * Returns TRUE in case this node represents an {@link java.lang.reflect.Array} value
     *
     * @return TRUE if {@link java.lang.reflect.Array} otherwise FALSE
     */
    boolean isArray();

    /**
     * Returns TRUE in case this node represents an {@link java.util.Collection} value
     *
     * @return TRUE if {@link java.util.Collection} otherwise FALSE
     */
    boolean isCollection();

    /**
     * Returns TRUE in case this node represents an {@link Double} value
     *
     * @return TRUE if {@link Double} otherwise FALSE
     */
    boolean isDouble();

    /**
     * Returns TRUE in case this node represents an {@link Boolean} value
     *
     * @return TRUE if {@link Boolean} otherwise FALSE
     */
    boolean isBoolean();

    /**
     * Returns TRUE in case this node represents an {@link Long} value
     *
     * @return TRUE if {@link Long} otherwise FALSE
     */
    boolean isLong();

    /**
     * Returns TRUE in case this node represents an {@link String} value
     *
     * @return TRUE if {@link String} otherwise FALSE
     */
    boolean isText();

    /**
     * Returns TRUE in case this node represents an {@link Integer} value
     *
     * @return TRUE if {@link Integer} otherwise FALSE
     */
    boolean isInteger();

    /**
     * Returns TRUE in case this node represents an {@link Byte} value
     *
     * @return TRUE if {@link Byte} otherwise FALSE
     */
    boolean isByte();

    /**
     * Returns TRUE in case this node represents an {@link Date} value
     *
     * @return TRUE if {@link Date} otherwise FALSE
     */
    boolean isDate();

    /**
     * Returns TRUE in case this node represents an {@link LocalTime} value
     *
     * @return TRUE if {@link LocalTime} otherwise FALSE
     */
    boolean isLocalTime();

    /**
     * Returns TRUE in case this node represents an {@link LocalDate} value
     *
     * @return TRUE if {@link LocalDate} otherwise FALSE
     */
    boolean isLocalDate();

    /**
     * Returns TRUE in case this node represents an {@link LocalDateTime} value
     *
     * @return TRUE if {@link LocalDateTime} otherwise FALSE
     */
    boolean isLocalDateTime();

    /**
     * Returns TRUE in case this node represents an {@link ZonedDateTime} value
     *
     * @return TRUE if {@link ZonedDateTime} otherwise FALSE
     */
    boolean isZonedDateTime();

    /**
     * Returns TRUE in case this node represents an {@link Character} value
     *
     * @return TRUE if {@link Character} otherwise FALSE
     */
    boolean isCharacter();

    /**
     * Returns TRUE in case this node represents an {@link CharSequence} value
     *
     * @return TRUE if {@link CharSequence} otherwise FALSE
     */
    boolean isCharSequence();

    /**
     * Returns TRUE in case this node represents an {@link Float} value
     *
     * @return TRUE if {@link Float} otherwise FALSE
     */
    boolean isFloat();

    /**
     * Returns TRUE in case this node represents an {@link BigDecimal} value
     *
     * @return TRUE if {@link BigDecimal} otherwise FALSE
     */
    boolean isBigDecimal();

    /**
     * Returns TRUE in case this node represents an {@link BigInteger} value
     *
     * @return TRUE if {@link BigInteger} otherwise FALSE
     */
    boolean isBigInteger();

    /**
     * Returns TRUE in case this node represents an {@link Enum} value
     *
     * @return TRUE if {@link Enum} otherwise FALSE
     */
    boolean isEnumerated();

    /**
     * Returns the size of the node.
     * When {@link #isSingular()} is TRUE the size is always 0
     *
     * @return node size
     */
    int size();

    /**
     * Returns the node representation as {@link Integer}
     * In case the representation cannot be converted to {@link Integer} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isInteger()} is TRUE
     *
     * @return @{@link Integer} representation
     */
    int asInt();

    /**
     * Returns the node representation as {@link Long}
     * In case the representation cannot be converted to {@link Long} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isLong()} is TRUE
     *
     * @return @{@link Long} representation
     */
    long asLong();

    /**
     * Returns the node representation as {@link String}
     * In case the representation cannot be converted to {@link String} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isText()} is TRUE
     *
     * @return @{@link String} representation
     */
    String asText();

    /**
     * Returns the node representation as {@link Boolean}
     * In case the representation cannot be converted to {@link Boolean} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isBoolean()} is TRUE
     *
     * @return @{@link Boolean} representation
     */
    boolean asBoolean();

    /**
     * Returns the node representation as {@link Double}
     * In case the representation cannot be converted to {@link Double} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isDouble()} is TRUE
     *
     * @return @{@link Double} representation
     */
    double asDouble();

    /**
     * Returns the node representation as {@link Float}
     * In case the representation cannot be converted to {@link Float} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isFloat()} is TRUE
     *
     * @return @{@link Float} representation
     */
    float asFloat();

    /**
     * Returns the node representation as {@link Byte}
     * In case the representation cannot be converted to {@link Byte} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isByte()} is TRUE
     *
     * @return @{@link Byte} representation
     */
    byte asByte();

    /**
     * Returns the node representation as {@link Date}
     * In case the representation cannot be converted to {@link Date} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isDate()} is TRUE
     *
     * @return @{@link Date} representation
     */
    Date asDate();

    /**
     * Returns the node representation as {@link LocalTime}
     * In case the representation cannot be converted to {@link LocalTime} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isLocalTime()} is TRUE
     *
     * @return @{@link LocalTime} representation
     */
    LocalTime asLocalTime();

    /**
     * Returns the node representation as {@link LocalDate}
     * In case the representation cannot be converted to {@link LocalTime} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isLocalDate()} is TRUE
     *
     * @return @{@link LocalDate} representation
     */
    LocalDate asLocalDate();

    /**
     * Returns the node representation as {@link LocalDateTime}
     * In case the representation cannot be converted to {@link LocalDateTime} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isLocalDateTime()} is TRUE
     *
     * @return @{@link LocalDateTime} representation
     */
    LocalDateTime asLocalDateTime();

    /**
     * Returns the node representation as {@link ZonedDateTime}
     * In case the representation cannot be converted to {@link ZonedDateTime} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isZonedDateTime()} is TRUE
     *
     * @return @{@link ZonedDateTime} representation
     */
    ZonedDateTime asZonedDateTime();

    /**
     * Returns the node representation as {@link Character}
     * In case the representation cannot be converted to {@link Character} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isCharacter()} is TRUE
     *
     * @return @{@link Character} representation
     */
    char asCharacter();

    /**
     * Returns the node representation as {@link CharSequence}
     * In case the representation cannot be converted to {@link CharSequence} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isCharSequence()} is TRUE
     *
     * @return @{@link CharSequence} representation
     */
    CharSequence asCharSequence();

    /**
     * Returns the node representation as {@link BigDecimal}
     * In case the representation cannot be converted to {@link BigDecimal} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isBigDecimal()} is TRUE
     *
     * @return @{@link BigDecimal} representation
     */
    BigDecimal asBigDecimal();

    /**
     * Returns the node representation as {@link BigInteger}
     * In case the representation cannot be converted to {@link BigInteger} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isBigInteger()} is TRUE
     *
     * @return @{@link BigInteger} representation
     */
    BigInteger asBigInteger();

    /**
     * Returns the node representation as {@link Enum}
     * In case the representation cannot be converted to {@link Enum} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isEnumerated()} is TRUE
     *
     * @return @{@link Enum} representation
     */
    Enum<?> asEnum();

    /**
     * Returns the node representation as {@link Class}
     * In case the representation cannot be converted to {@link Class} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isClass()} is TRUE
     *
     * @return @{@link Class} representation
     */
    Class<?> asClass();


    /**
     * Returns the node representation as {@link Method}
     * In case the representation cannot be converted to {@link Method} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isMethod()} is TRUE
     *
     * @return @{@link Method} representation
     */
    Method asMethod();

    /**
     * Returns the node representation as {@link Field}
     * In case the representation cannot be converted to {@link Field} an {@link UnsupportedOperationException} is thrown
     * , in order to avoid the {@link UnsupportedOperationException} , this method should be called when {@link #isField()} is TRUE
     *
     * @return @{@link Field} representation
     */
    Field asField();


    /**
     * Returns the type which this node represents
     *
     * @return type of the node
     */
    Class<?> getType();

    interface Builder {

        Node build();

        Builder setNode(String fieldName, Node value);

        Builder setNode(String fieldName, String value);

        Builder setNode(String fieldName, CharSequence value);

        Builder setNode(String fieldName, int value);

        Builder setNode(String fieldName, Integer value);

        Builder setNode(String fieldName, Long value);

        Builder setNode(String fieldName, long value);

        Builder setNode(String fieldName, char value);

        Builder setNode(String fieldName, Character value);

        Builder setNode(String fieldName, float value);

        Builder setNode(String fieldName, Float value);

        Builder setNode(String fieldName, double value);

        Builder setNode(String fieldName, Double value);

        Builder setNode(String fieldName, boolean value);

        Builder setNode(String fieldName, Boolean value);

        Builder setNode(String fieldName, byte value);

        Builder setNode(String fieldName, Byte value);

        Builder setNode(String fieldName, BigDecimal value);

        Builder setNode(String fieldName, BigInteger value);

        Builder setNode(String fieldName, Date value);

        Builder setNode(String fieldName, LocalTime value);

        Builder setNode(String fieldName, LocalDate value);

        Builder setNode(String fieldName, LocalDateTime value);

        Builder setNode(String fieldName, ZonedDateTime value);

        Builder setNode(String fieldName, Method value);

        Builder setNode(String fieldName, Class<?> value);

        Builder setNode(String fieldName, Field value);

        Builder setNode(String fieldName, Enum<?> value);

        Builder setNode(String fieldName, String[] value);

        Builder setNode(String fieldName, CharSequence[] value);

        Builder setNode(String fieldName, int[] value);

        Builder setNode(String fieldName, Integer[] value);

        Builder setNode(String fieldName, Long[] value);

        Builder setNode(String fieldName, long[] value);

        Builder setNode(String fieldName, char[] value);

        Builder setNode(String fieldName, Character[] value);

        Builder setNode(String fieldName, float[] value);

        Builder setNode(String fieldName, Float[] value);

        Builder setNode(String fieldName, double[] value);

        Builder setNode(String fieldName, Double[] value);

        Builder setNode(String fieldName, boolean[] value);

        Builder setNode(String fieldName, Boolean[] value);

        Builder setNode(String fieldName, byte[] value);

        Builder setNode(String fieldName, Byte[] value);

        Builder setNode(String fieldName, BigDecimal[] value);

        Builder setNode(String fieldName, BigInteger[] value);

        Builder setNode(String fieldName, Date[] value);

        Builder setNode(String fieldName, Enum<?>[] value);

        Builder setNode(String fieldName, LocalTime[] value);

        Builder setNode(String fieldName, LocalDate[] value);

        Builder setNode(String fieldName, LocalDateTime[] value);

        Builder setNode(String fieldName, ZonedDateTime[] value);

        Builder setNode(String fieldName, Method[] value);

        Builder setNode(String fieldName, Class<?>[] value);

        Builder setNode(String fieldName, Field[] value);

        <T> Builder setNode(String fieldName, Collection<T> value);

        Builder setType(Class<?> type);

    }

}
