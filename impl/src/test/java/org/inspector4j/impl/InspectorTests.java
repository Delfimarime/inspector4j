package org.inspector4j.impl;

import org.inspector4j.api.Inspector;
import org.inspector4j.api.Node;
import org.inspector4j.impl.model.Person;
import org.junit.Assert;
import org.junit.Test;

public class InspectorTests {

    @Test
    public void run() {
        Inspector instance = new InspectorImpl();
        Node node = instance.inspect(Person.builder().age(12).gender(null).name("John").build());
        Assert.assertEquals(node.size(),3);
    }

}
