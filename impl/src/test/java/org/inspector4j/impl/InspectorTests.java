package org.inspector4j.impl;

import org.inspector4j.api.Inspector;
import org.inspector4j.api.Node;
import org.inspector4j.impl.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class InspectorTests {

    @Test
    public void single() {
        Inspector instance = new InspectorImpl();
        Node node = instance.inspect(getSingle());
        Assert.assertEquals(4, node.size());
    }

    @Test
    public void withChildren() {

        Person[] children = new Person[2];
        children[0] = Person.builder().age(5).gender(null).name("Joseph").build();
        children[1] = Person.builder().age(1).gender(null).name("Jonathan").build();

        Object object = Person.builder().age(30).gender(null).name("John").children(children).build();
        Inspector instance = new InspectorImpl();
        Node node = instance.inspect(object);
        Assert.assertEquals(node.size(), 4);

        System.out.println(node.get("children"));

    }

    @Test
    public void run() throws Exception {
        Inspector instance = new InspectorImpl();
        Method objectConstructor = Factory.class.getMethod("create", String.class, Type.class);
        Method personConstructor = Factory.class.getMethod("create", Person.class, Type.class);

        System.out.println(instance.inspect(objectConstructor, new Object[]{"Necklace", Type.GOLD}));
        System.out.println(instance.inspect(personConstructor, new Object[]{getFamilyFather(), Type.GOLD}));

    }

    private Person getSingle() {
        return Person.builder().age(12).gender(null).name("John").build();
    }

    private Person getFamilyFather(){
        Person[] children = new Person[2];
        children[0] = Person.builder().age(5).gender(null).name("Joseph").build();
        children[1] = Person.builder().age(1).gender(null).name("Jonathan").build();

        return Person.builder().age(30).gender(null).name("John").children(children).build();
    }

}
