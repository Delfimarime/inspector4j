package org.inspector4j.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface NodeBuilder {

    Node build();

    NodeBuilder setNode(String fieldName, Node value);

    NodeBuilder setNode(String fieldName, String value);

    NodeBuilder setNode(String fieldName, CharSequence value);

    NodeBuilder setNode(String fieldName, int value);

    NodeBuilder setNode(String fieldName, Integer value);

    NodeBuilder setNode(String fieldName, Long value);

    NodeBuilder setNode(String fieldName, long value);

    NodeBuilder setNode(String fieldName, char value);

    NodeBuilder setNode(String fieldName, Character value);

    NodeBuilder setNode(String fieldName, float value);

    NodeBuilder setNode(String fieldName, Float value);

    NodeBuilder setNode(String fieldName, double value);

    NodeBuilder setNode(String fieldName, Double value);

    NodeBuilder setNode(String fieldName, boolean value);

    NodeBuilder setNode(String fieldName, Boolean value);

    NodeBuilder setNode(String fieldName, byte value);

    NodeBuilder setNode(String fieldName, Byte value);

    NodeBuilder setNode(String fieldName, BigDecimal value);

    NodeBuilder setNode(String fieldName, BigInteger value);

    NodeBuilder setNode(String fieldName, Date value);

    NodeBuilder setNode(String fieldName, Timestamp value);

    NodeBuilder setNode(String fieldName, LocalTime value);

    NodeBuilder setNode(String fieldName, LocalDate value);

    NodeBuilder setNode(String fieldName, LocalDateTime value);

    NodeBuilder setNode(String fieldName, ZonedDateTime value);

    <T> NodeBuilder setNode(String fieldName, T[] value);

    <T, K> NodeBuilder setNode(String fieldName, Map<T, K> value);

    <T> NodeBuilder setNode(String fieldName, Collection<T> value);

}
