package org.inspector4j.impl;

import org.inspector4j.Inspector;
import org.inspector4j.api.InspectionResult;
import org.inspector4j.api.Inspector4J;
import org.inspector4j.api.Node;
import org.inspector4j.impl.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class InspectorTests {

    @Test
    public void single() {
        Node node = Inspector4J.get().inspect(getSingle());
        Assert.assertEquals(4, node.size());
    }

    @Test
    public void withChildren() {
        Object object = getFamilyFather();
        Node node = Inspector4J.get().inspect(object);
        Assert.assertEquals(node.size(), 4);

        System.out.println(node.get("children"));

    }

    @Test
    public void run() throws Exception {
        Inspector instance = Inspector4J.get();
        Method constructor = Factory.class.getMethod("create", Person.class, Type.class);

        // System.out.println(instance.inspect(objectConstructor, new Object[]{"Necklace", Type.GOLD}));
        // System.out.println(instance.inspect(personConstructor, new Object[]{getFamilyFather(), Type.WOOD}));
        InspectionResult node = instance.inspect(constructor, new Object[]{getFriendly(), Type.WOOD});

        System.out.println(node.get("person").toMap());
        System.out.println(Arrays.asList((Object[]) node.get("person").get("friends").get(0).toMap().get("friends")).get(0));

       /*
        System.out.println(node.get("person").get("value").get(0).asText());

        System.out.println(node.get("person").get("friends").get(0).get("name").asText());

        System.out.println(node.get("person").get("friends").get(0).get("friends").get(0).get("name").asText());

        System.out.println(node.get("person").get("friends").get(0).get("friends").get(0).get("friends").get(0).get("name").asText());

        System.out.println(node.get("person").get("friends").get(0).get("friends").get(0).get("friends").get(0).get("friends").get(0).get("name").asText());
*/
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

    /**
     * Creates a male {@link Person} which is friend of female {@link Person} where both are friends of each other
     * @return male {@link Person}
     */
    private Person getFriendly() {
        Person adam = Person.builder().age(30).gender('M').name("Adam").children(null).build();
        Person lilith = Person.builder().age(29).gender('F').name("Lilith").children(null).build();

        lilith.setFriends(new Person[]{adam});
        adam.setFriends(new Person[]{lilith});

        return adam;
    }

}
