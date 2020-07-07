# INSPECTOR4J

INSPECTOR4J , **IN**(PUT)(INS)**PECTOR** for Java , is a Java library which provides method and arguments inspect and generates nodes which represents both.
 
## Installation

Use the package manager [maven](https://maven.apache.org/) to install inspector4j.
On your project maven **POM.xml** add bellow dependencies:

```xml
  <!-- API -->
  <dependency>
    <groupId>org.inspector4j</groupId>
    <artifactId>api</artifactId>
    <version>1.0.0-RELEASE</version>
  </dependency>

  <!-- API IMPLEMENTATION -->
  <dependency>
    <groupId>org.inspector4j</groupId>
    <artifactId>impl</artifactId>
    <version>1.0.0-RELEASE</version>
  </dependency>
```

## Usage
To inspect a method and parameters the first step is to get an inspector instance which could be retrieved by the invocation of Inspect4J.get(), and then invoke inspect Method from the Inspector instance.\
Consider the bellow example where we have a Person class defined as :
```Java
package org.inspector4j;

public class Person implements Serializable {
    private static String[] value = {"org","inspector4j"};

    private int age;
    private String name;
    private Character gender;
    private Person[] children;
    private Person[] friends;

    // GETTERS AND SETTERS

    public static Builder builder(){
        // CREATES A BUILDER INSTANCE AND RETURNS IT
    }   
    
    public static class Builder {
        // BUILDER CLASS METHODS
    }

}
```

Consider Type an Enum defined as :
```Java
package org.inspector4j;

public enum Type {
    GOLD, SILVER, BRONZE, WOOD, STONE;
}
```
Consider Factory class which solo purpose is to receive an Object, and a Type of material to produce the same Object but made of the Type indicated. Find the definition bellow :
```Java
package org.inspector4j;

import org.inspector4j.impl.model.Person;

public class Factory {

    public String create(String name, Type type) {
        return name + " of " + type;
    }

    public <T> Person create(Person person, Type type) {
        throw new UnsupportedOperationException("Cannot create Person of " + type);
    }

}
```
Finally, consider the Example class which must inspect ***create*** method from ***Factory*** class
```Java
package org.inspector4j;

import org.inspector4j.Inspector;
import org.inspector4j.api.Analysis;
import org.inspector4j.api.Inspect4J;
import org.inspector4j.api.Node;

public class Example {

    public static void main (String[]args){
        Inspector instance = Inspect4J.get(); // GETS INSPECTOR INSTANCE WHICH IS USED TO INSPECT 
        Method method = Factory.class.getMethod("create", Person.class, Type.class); // METHOD TO BE INSPECT FROM CLASS Factory
        Analysis node = instance.inspect(method, new Object[]{getFriendly(), Type.WOOD}); // INSPECTS THE METHOD WITH THE ARGS PASSED ON THE METHOD AND PRODUCES AN ANALYSIS WHICH REPRESENTS THE INSPECTION 
        
        System.out.println(node.get("person").get("value").get(0).asText()); // PICK PARAMETER WITH NAME person , THEN PICK ATTRIBUTE value WITHIN THE PARAMETER , THEN GET ELEMENT AT INDEX 0 FROM THE ATTRIBUTE AND LASTLY LASTLY RETURN THE ATTRIBUTE
        System.out.println(node.get("person").get("friends").get(0).get("name").asText()); // PICK PARAMETER WITH NAME person ( WHICH IS Adam ), THEN PICK ATTRIBUTE friends WITHIN THE PARAMETER , THEN GET ELEMENT AT INDEX 0 (WHICH IS PERSON WITH NAME Lilith ) FROM THE ATTRIBUTE , THEN PICK name ATTRIBUTE FROM LAST ATTRIBUTE (PERSON with name Lilith)  AND LASTLY RETURN THE ATTRIBUTE AS TEXT
        System.out.println(node.get("person").get("friends").get(0).get("friends").get(0).get("name").asText());
        System.out.println(node.get("person").get("friends").get(0).get("friends").get(0).get("friends").get(0).get("name").asText());
        System.out.println(node.get("person").get("friends").get(0).get("friends").get(0).get("friends").get(0).get("friends").get(0).get("name").asText());
    }
    
     /**
      * Creates a male {@link Person} which is friend of female {@link Person} where both are friends of each other
      * @return male {@link Person}
      */
    public static Person getFriendly(){
      Person male = Person.builder().age(30).gender(null).name("Adam").children(null).build();
      Person female = Person.builder().age(29).gender(null).name("Lilith").children(null).build();
      female.setFriends(new Person[]{male});
      male.setFriends(new Person[]{female});
      return male;
    }   

}
```
The above example should create a Node that represents a ***Person*** object with ***name*** Adam with 30 as ***age*** and its friends with a ***Person*** object with ***name*** Lilith and age 29 and that is friend with adam.
 ``` Console
org
Lilith
Adam
Lilith
Adam
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Apache License](http://www.apache.org/licenses/)


## Issue Tracking

Issues, bugs, and feature requests should be submitted to the 
[JIRA issue tracking system for this project].
Pull request on GitHub are welcome, but please open a ticket in the JIRA issue tracker first, and mention the 
JIRA issue in the Pull Request.

## Building From Source

Inspector4j requires Apache Maven 3.x. To build from source and install to your local Maven repository, execute the following:

```sh
mvn install - f api
mvn install - f impl
```