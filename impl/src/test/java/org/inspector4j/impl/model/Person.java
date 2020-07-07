package org.inspector4j.impl.model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@ToString()
@Builder(toBuilder = true)
public class Person implements Serializable {
    private static String[] value = {"org","inspector4j"};

    @Setter
    private int age;
    @Setter
    private String name;
    @Setter
    private Character gender;
    @Setter
    private Person[] children;
    @Setter
    private Person[] friends;

}
