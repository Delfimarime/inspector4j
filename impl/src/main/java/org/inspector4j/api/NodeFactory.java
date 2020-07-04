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

public interface NodeFactory {

    Node build();

    Node build(Object value);

    Node build(String value);

    Node build(CharSequence value);

    Node build(int value);

    Node build(Integer value);

    Node build(Long value);

    Node build(long value);

    Node build(char value);

    Node build(Character value);

    Node build(float value);

    Node build(Float value);

    Node build(double value);

    Node build(Double value);

    Node build(boolean value);

    Node build(Boolean value);

    Node build(byte value);

    Node build(Byte value);

    Node build(BigDecimal value);

    Node build(BigInteger value);

    Node build(Date value);

    Node build(Timestamp value);

    Node build(LocalTime value);

    Node build(LocalDate value);

    Node build(LocalDateTime value);

    Node build(ZonedDateTime value);

    <T> Node build(T[] value);

    <T, K> Node build(Map<T, K> value);

    <T> Node build(Collection<T> value);

    NodeBuilder builder();

}
