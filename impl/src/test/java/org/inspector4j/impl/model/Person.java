package org.inspector4j.impl.model;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@ToString()
@Builder(toBuilder = true)
public class Person implements Serializable {

    private int age;
    private String name;
    private Character gender;
    private Person[] children;

}
