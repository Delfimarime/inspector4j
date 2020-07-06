package org.inspector4j.api;

import org.inspector4j.Inspector;

import java.util.ServiceLoader;

public final class Inspect4J {

    public static Inspector get() {
        return ServiceLoader.load(Inspector.class).iterator().next();
    }

}
