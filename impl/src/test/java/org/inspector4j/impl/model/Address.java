package org.inspector4j.impl.model;

import java.io.Serializable;

public class Address implements Serializable {

   private String avenue;
   private String number;

   public Address(){}

    public Address(String avenue, String number) {
        this.avenue = avenue;
        this.number = number;
    }

    public String getAvenue() {
        return avenue;
    }

    public void setAvenue(String avenue) {
        this.avenue = avenue;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
