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
        Method method = Factory.class.getMethod("create", Person.class, Type.class);

        InspectionResult node = instance.inspect(method, new Object[]{getFriendly(), Type.WOOD});

        System.out.println(node.get("person").asMap()); // PICK PARAMETER WITH NAME person , THEN TRANSFORMS EVERY ATTRIBUTE AS MAP OF MAPS
        System.out.println(node.get("person").toMap()); // PICK PARAMETER WITH NAME person , THEN TRANSFORMS EVERY ATTRIBUTE AS MAP OF MAPS
        System.out.println(node.get("person").get("value").get(0).asText()); // PICK PARAMETER WITH NAME person , THEN PICK ATTRIBUTE value WITHIN THE PARAMETER , THEN GET ELEMENT AT INDEX 0 FROM THE ATTRIBUTE AND LASTLY LASTLY RETURN THE ATTRIBUTE
        System.out.println(node.get("person").get("friends").get(0).get("name").asText()); // PICK PARAMETER WITH NAME person ( WHICH IS Adam ), THEN PICK ATTRIBUTE friends WITHIN THE PARAMETER , THEN GET ELEMENT AT INDEX 0 (WHICH IS PERSON WITH NAME Lilith ) FROM THE ATTRIBUTE , THEN PICK name ATTRIBUTE FROM LAST ATTRIBUTE (PERSON with name Lilith)  AND LASTLY RETURN THE ATTRIBUTE AS TEXT
        System.out.println(node.get("person").get("friends").get(0).asMap());
        System.out.println(node.get("person").get("friends").get(0).get("friends").get(0).get("name").asText());
        System.out.println(node.get("person").get("friends").get(0).get("friends").get(0).get("friends").get(0).get("name").asText());
        System.out.println(node.get("person").get("friends").get(0).get("friends").get(0).get("friends").get(0).get("friends").get(0).get("name").asText());

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
