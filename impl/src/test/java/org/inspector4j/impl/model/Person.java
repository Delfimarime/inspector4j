package org.inspector4j.impl.model;

import java.io.Serializable;

public class Person implements Serializable {

    private int age;
    private String name;
    private Character gender;

    public Person(){
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

}
