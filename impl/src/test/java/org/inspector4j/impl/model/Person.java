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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, gender);
    }
}
