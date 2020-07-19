package org.inspector4j.impl.model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.inspector4j.api.Secret;

import java.io.Serializable;
import java.util.Objects;

@Builder(toBuilder = true)
public class Person implements Serializable {
    private static final String[] value = {"org","inspector4j"};

    @Setter
    private int age;

    @Setter
    private String name;

    @Setter
    private Character gender;

    @Setter
    private Person[] children;

    @Setter
    @Secret
    private Person[] friends;

}
