package org.inspector4j.impl;

import org.inspector4j.Inspector;
import org.inspector4j.api.Analysis;
import org.inspector4j.api.Inspect4J;
import org.inspector4j.api.Node;
import org.inspector4j.impl.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class InspectorTests {

    @Test
    public void single() {
        Node node = Inspect4J.get().inspect(getSingle());
        Assert.assertEquals(4, node.size());
    }

    @Test
    public void withChildren() {
        Object object = getFamilyFather();
        Node node = Inspect4J.get().inspect(object);
        Assert.assertEquals(node.size(), 4);

        System.out.println(node.get("children"));

    }

    @Test
    public void run() throws Exception {
        Inspector instance = Inspect4J.get();
        Method objectConstructor = Factory.class.getMethod("create", String.class, Type.class);
        Method personConstructor = Factory.class.getMethod("create", Person.class, Type.class);

        // System.out.println(instance.inspect(objectConstructor, new Object[]{"Necklace", Type.GOLD}));
        // System.out.println(instance.inspect(personConstructor, new Object[]{getFamilyFather(), Type.WOOD}));
        Analysis node = instance.inspect(personConstructor, new Object[]{getFriendly(), Type.WOOD});

        System.out.println(node.get("person").get("friends").get(0).get("name"));

        System.out.println(node.get("person").get("friends").get(0).get("friends").get(0).get("name"));

        System.out.println(node.get("person").get("friends").get(0).get("friends").get(0).get("friends").get(0).get("name"));

        System.out.println(node.get("person").get("friends").get(0).get("friends").get(0).get("friends").get(0).get("friends").get(0).get("name"));

    }

    private Person getSingle() {
        return Person.builder().age(12).gender(null).name("John").build();
    }

    private Person getFamilyFather() {
        Person[] children = new Person[2];
        children[0] = Person.builder().age(5).gender(null).name("Joseph").build();
        children[1] = Person.builder().age(1).gender(null).name("Jonathan").build();

        return Person.builder().age(30).gender(null).name("John").children(children).build();
    }

    private Person getFriendly() {
        Person adam = Person.builder().age(30).gender(null).name("Adam").children(null).build();
        Person lilith = Person.builder().age(29).gender(null).name("Lilith").children(null).build();

        lilith.setFriends(new Person[]{adam});
        adam.setFriends(new Person[]{lilith});

        return adam;
    }

}
