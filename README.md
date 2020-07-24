# INSPECTOR4J

INSPECTOR4J , **IN**(PUT)(INS)**PECTOR** for Java , is a Java library which provides method and arguments inspect and generates nodes which represents both.
 
## Usage

Use the package manager [maven](https://maven.apache.org/) to install inspector4j.
On your project maven **POM.xml** add bellow dependencies:

```xml
  <!-- API -->
  <dependency>
    <groupId>org.inspector4j</groupId>
    <artifactId>api</artifactId>
    <version>1.0.0-RELEASE</version>
  </dependency>

  <!-- IMPLEMENTATION -->
  <dependency>
    <groupId>org.inspector4j</groupId>
    <artifactId>impl</artifactId>
    <version>1.0.0-RELEASE</version>
  </dependency>
```

## Configuration
INSPECTOR4J contains 2 configurations properties which are expected at the JVM system properties , which are:

**org.inspector4j.secrets.is-aware** - Determines whether by default secret's should be inspected , by default is  false. Any **java.reflect.Parameter** or **java.reflect.Field** or **java.reflect.Type** annotated with **@org.inspector4j.api.Secret** are considered secrets;

**org.inspector4j.secrets.is-aware.override** - Determines whether scecret **org.inspector4j.secrets.is-aware** is overridable , by default is false. In case **org.inspector4j.Inspector** method **public InspectionResult inspect(Method method, Object[] args, boolean inspectAll)** with **inspectAll** is **true** and this configuration is **false** meanwhile **org.inspector4j.secrets.is-aware** configuration is **false** secrets will not be inspected.


## Getting Started
Here's a quick teaser of an example for this library.  Let's consider the a factory class that contains a method **public String createCompliment(String name, @Secret String surname)** which receives a name and surname and generates a compliment according to the argument's.
```Java
package org.inspector4j;

import org.inspector4j.*;

public class Factory {

  public String createCompliment(String name , @Secret String surname) {
    if(surname == null && name == null ) {
        throw new IllegalArgumentException(" Name and surname mustn't be null at the same time");
    } else if(name != null && surname != null ) {
        return "Hello " +surname+" , "+ name;
    } else if(name != null ) {
        return "Hello " + name;
    } else {
        return "Hello " + surname;
    }
  }

}
```
Consider an **Example** class which contains a **main** method that explores different scenarios , as describe bellow:
```Java
package org.inspector4j;

import org.inspector4j.Inspector;
import org.inspector4j.api.InspectionResult;
import org.inspector4j.api.Inspector4J;
import org.inspector4j.api.Node;

public class Example {

    public static void main ( String[] args ) {

        Inspector instance = Inspector4J.get(); // gets inspector instance which is responsible for inspection 
        Method method = Factory.class.getMethod("create" , String.class , String.class ); // retrieves the method intended to be inspected 
        InspectResult returnObject = instance.inspect( method , new Object[] { "John" , "Doe" } , true ); // returns Inspectresult  which is a node that represents the method and parameters which were invoked
        
        System.out.println(returnObject.asMap()); // asMap() method returns the arguments as Map , in this case the expected as output for this print  is { name = John } beacuse surname is annotated with @org.inspector4j.api.Secret and org.inspector4j.secrets.is-aware is false which ignores the inspectAll parameter
     
        System.setProperty("org.inspector4j.secrets.is-aware.override", "true"); // Change org.inspector4j.secrets.is-aware.override

        InspectResult returnObject = instance.inspect( method , new Object[] { "John" , "Doe" } , true ); // returns Inspectresult  which is a node that represents the method and parameters which were invoked
        
        System.out.println(returnObject.asMap()); // asMap() method returns the arguments as Map , in this case the expected as output for this print  is { name = John , surname = Doe } beacuse org.inspector4j.secrets.is-aware is false but  org.inspector4j.secrets.is-aware.override is true  which means the inspectAll overrides org.inspector4j.secrets.is-aware 


        System.setProperty("org.inspector4j.secrets.is-aware", "true"); // Change org.inspector4j.secrets to true which means secret's must be inspected even when explicit indicated not to

        InspectResult returnObject = instance.inspect( method , new Object[] { "John" , "Doe" } , false ); // returns Inspectresult  which is a node that represents the method and parameters which were invoked
        
        System.out.println(returnObject.asMap()); // asMap() method returns the arguments as Map , in this case the expected as output for this print  is { name = John , surname = Doe } beacuse org.inspector4j.secrets.is-aware is true which means secret's must be inspected
    
    }

}
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
mvn install -f api
mvn install -f impl
```