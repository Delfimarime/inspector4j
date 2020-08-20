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
INSPECTOR4J configuration manipulation depends on **inspector4j.xml** which must be located in **resources** directory.
```xml
<Configuration xmlns="http://inspector4j.org">
    <Root scope="ATTRIBUTE" override="false"/>
    <Inspector name="org.inspector4j.impl.InspectorTests" scope="SECRET" override="false"/>
</Configuration>
```
The code above represents the **inspector4j.xml** configuration structure. The **Root** element represents the default configuration that will apply to all Inspector instances not declared in the configuration file , and
The Inspector element , which is repeatable , specifies the configuration to be applied to a specific Inspector whose **name** attribute matches the name one defined.
The **Root** & **Inspector** element's contains two attributes which are:  

**scope** - can be ATTRIBUTE (ignores secret inspection) OR SECRET (include secret inspection )  , by default the value is ATTRIBUTE. Any **java.reflect.Parameter** or **java.reflect.Field** or **java.reflect.Type** annotated with **@org.inspector4j.api.Secret** are considered secrets;

**override** - Determines whether **scope** is overridable , by default is false. In case **org.inspector4j.Inspector** method **public InspectionResult inspect(Method method, Object[] args, boolean inspectAll)** with **inspectAll** is **true** and this configuration is **false** meanwhile **scope** configuration is **ATTRIBUTE** secrets will not be inspected.


## Getting Started
Here's a quick teaser of an example for this library.  Let's consider the factory class which contains a method **public String createCompliment(String name, @Secret String surname)** which receives a name and surname and generates a compliment according to the argument's.
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
Consider an **Example** class which contains a **main** method that explores different scenarios , as describe:
### Scenario 1
```xml
<Configuration xmlns="http://inspector4j.org">
    <Inspector name="org.inspector4j.Example" scope="ATTRIBUTE" override="false"/>
</Configuration>
```

```Java
package org.inspector4j;

import org.inspector4j.Inspector;
import org.inspector4j.api.InspectionResult;
import org.inspector4j.api.Inspector4J;
import org.inspector4j.api.Node;

public class Example {

    public static void main ( String[] args ) {

        Inspector instance = Inspector4J.get(Example.class); // gets inspector instance which is responsible for inspection 
        Method method = Factory.class.getMethod("create" , String.class , String.class ); // retrieves the method intended to be inspected 
        InspectResult returnObject = instance.inspect( method , new Object[] { "John" , "Doe" } , true ); // returns InspectedResult  which is a node that represents the method and parameters which were invoked
        System.out.println(returnObject.asMap()); // asMap() method returns the arguments as Map , in this case the expected as output for this print  is { name = John } because surname is annotated with @org.inspector4j.api.Secret and the scope is ATTRIBUTE which ignores the inspectAll parameter and override is false
    }

}
```


### Scenario 2

```xml
<Configuration xmlns="http://inspector4j.org">
    <Root scope="ATTRIBUTE" override="true"/>
</Configuration>
```

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

        InspectResult returnObject = instance.inspect( method , new Object[] { "John" , "Doe" } , true ); // returns InspectedResult  which is a node that represents the method and parameters which were invoked        
        System.out.println(returnObject.asMap()); // asMap() method returns the arguments as Map , in this case the expected as output for this print  is { name = John , surname = Doe } because scope  is ATTRIBUTE but override is true  which means the inspectAll overrides ATTRIBUTE scope

    }

}
```

### Scenario 3

```xml
<Configuration xmlns="http://inspector4j.org">
    <Root scope="SECRET" override="false"/>
</Configuration>
```

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
        InspectResult returnObject = instance.inspect( method , new Object[] { "John" , "Doe" } , false ); // returns InspectedResult  which is a node that represents the method and parameters which were invoked
        System.out.println(returnObject.asMap()); // asMap() method returns the arguments as Map , in this case the expected as output for this print  is { name = John , surname = Doe } because scope is SECRET which means secret's must be inspected   
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