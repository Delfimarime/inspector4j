package org.inspector4j.impl;

import org.inspector4j.api.Inspector;
import org.inspector4j.impl.model.Person;
import org.junit.Test;

public class InspectorTests {

    @Test
    public void run() {
        Inspector instance = new InspectorImpl();
        instance.inspect(new Person());
    }

}
