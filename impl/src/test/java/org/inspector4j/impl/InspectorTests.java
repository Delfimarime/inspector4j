package org.inspector4j.impl;

import org.inspector4j.api.Inspector;
import org.inspector4j.api.Node;
import org.inspector4j.impl.model.Person;
import org.junit.Assert;
import org.junit.Test;

public class InspectorTests {

    @Test
    public void single() {
        Object object=Person.builder().age(12).gender(null).name("John").build();
        Inspector instance = new InspectorImpl();
        Node node = instance.inspect(object);
        Assert.assertEquals(node.size(),4);
        System.out.println(node);
    }


    @Test
    public void withChildren() {
        Person[] children = new Person[2];
        children[0]= Person.builder().age(5).gender(null).name("Joseph").build();
        children[1]= Person.builder().age(1).gender(null).name("Jonathan").build();

        Object object=Person.builder().age(30).gender(null).name("John").children(children).build();
        Inspector instance = new InspectorImpl();
        Node node = instance.inspect(object);
        Assert.assertEquals(node.size(),4);
        System.out.println(node);
    }

}
