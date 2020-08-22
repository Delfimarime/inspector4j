package org.inspector4j.impl;

import org.inspector4j.api.Inspector;
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
    public void run() throws Exception {
        run(false);
        run(true);
    }


    private void run(boolean isSecretAware) throws Exception {

        Inspector instance = Inspector4J.get(InspectorTests.class);
        Method method = Factory.class.getMethod("create", Person.class, Type.class);
        InspectionResult node = instance.inspect(method, new Object[]{getFriendly(), Type.WOOD, isSecretAware});

        Assert.assertEquals("Method must contain 2 parameters", 2, node.size());
        Assert.assertTrue("Method must have  person and type as parameters ", node.get("person") != null && node.get("type") != null);

        Assert.assertTrue("Type parameter must be an enum", node.get("type").isEnumerated());
        Assert.assertEquals("Type parameter must be an enum and equals to Type.WOOD", Type.WOOD, node.get("type").asEnum());

        assertObject(node.get("person"), isSecretAware);

        if (isSecretAware) {
            assertObject(node.get("person").get("friends").get(0), true);
        }

    }

    private void assertObject(Node node, boolean isSecretAware) {
        Assert.assertTrue("Person must have gender, and children , and name , and value " + (isSecretAware ? ",and friends " : "") + "and age ", node.get("gender") != null && node.get("children") != null && node.get("name") != null && node.get("value") != null && (!isSecretAware || node.get("friends") != null) && node.get("age") != null);

        Node child = node.get("value");
        Assert.assertEquals("Person value attribute must have 2 as size", 2, child.size());

        Assert.assertTrue("Person value attribute in index [0] must be Text", child.get(0).isText());
        Assert.assertEquals("Person value attribute in index [0] must be org", "org", child.get(0).asText());

        Assert.assertTrue("Person value attribute in index [1] must be Text", child.get(1).isText());
        Assert.assertEquals("Person value attribute in index [1] must be inspector4j", "inspector4j", child.get(1).asText());

    }


    /**
     * Creates a male {@link Person} which is friend of female {@link Person} where both are friends of each other
     *
     * @return male {@link Person}
     */
    private static Person getFriendly() {
        Person adam = Person.builder().age(30).gender('M').name("Adam").children(null).build();
        Person lilith = Person.builder().age(29).gender('F').name("Lilith").children(null).build();

        lilith.setFriends(new Person[]{adam});
        adam.setFriends(new Person[]{lilith});

        return adam;
    }

}
