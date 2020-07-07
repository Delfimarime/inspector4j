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

public interface NodeFactory {

    Node create();

    Node nullOf(Class<?>type);

    Node create(String value);

    Node create(CharSequence value);

    Node create(int value);

    Node create(Integer value);

    Node create(Long value);

    Node create(long value);

    Node create(char value);

    Node create(Character value);

    Node create(float value);

    Node create(Float value);

    Node create(double value);

    Node create(Double value);

    Node create(boolean value);

    Node create(Boolean value);

    Node create(byte value);

    Node create(Byte value);

    Node create(BigDecimal value);

    Node create(BigInteger value);

    Node create(Date value);

    Node create(Enum<?> value);

    Node create(LocalTime value);

    Node create(LocalDate value);

    Node create(LocalDateTime value);

    Node create(ZonedDateTime value);

    Node create(Method value);

    Node create(Class<?> value);

    Node create(Field value);

    Node create(String[] value);

    Node create(CharSequence[] value);

    Node create(int[] value);

    Node create(Integer[] value);

    Node create(Long[] value);

    Node create(long[] value);

    Node create(char[] value);

    Node create(Character[] value);

    Node create(float[] value);

    Node create(Float[] value);

    Node create(double[] value);

    Node create(Double[] value);

    Node create(boolean[] value);

    Node create(Boolean[] value);

    Node create(byte[] value);

    Node create(Byte[] value);

    Node create(BigDecimal[] value);

    Node create(BigInteger[] value);

    Node create(Date[] value);

    Node create(Enum<?>[] value);

    Node create(LocalTime[] value);

    Node create(LocalDate[] value);

    Node create(LocalDateTime[] value);

    Node create(ZonedDateTime[] value);

    Node create(Method[] value);

    Node create(Class<?>[] value);

    Node create(Field[] value);

    <T> Node create(Collection<T> value);

    Node.Builder newBuilder();

}
