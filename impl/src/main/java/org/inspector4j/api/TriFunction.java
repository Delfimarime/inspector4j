package org.inspector4j.api;

@FunctionalInterface
public interface TriFunction<T, Y, U, R> {
    R apply(T t, Y y, U u);
}
