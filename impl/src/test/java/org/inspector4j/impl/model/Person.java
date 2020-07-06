package org.inspector4j.impl.model;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@ToString()
@Builder(toBuilder = true)
public class Person implements Serializable {

    private final int age;
    private final String name;
    private final Character gender;
    private final Person[] children;

}
