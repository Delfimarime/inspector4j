package org.inspector4j.impl;

import org.inspector4j.api.Secret;
import org.inspector4j.impl.model.Person;

public class Factory {

    public String create(String name, Type type) {
        return name + " of " + type;
    }

    public String create(Person person, Type type) {
        throw new UnsupportedOperationException("Cannot create Person of " + type);
    }

}
