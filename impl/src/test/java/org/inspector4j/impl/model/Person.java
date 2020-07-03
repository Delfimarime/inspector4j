package org.inspector4j.impl.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Builder(toBuilder = true)
public class Person implements Serializable {

    private int age;
    private String name;
    private Character gender;

}
