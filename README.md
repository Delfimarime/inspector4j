# INSPECTOR4J

INSPECTOR4J , **IN**(PUT)(INS)**PECTOR** for Java , is a Java library which provides method and arguments inspect and generates nodes which represents both.
 
## Usage

Use the package manager [maven](https://maven.apache.org/) to install inspector4j.
On your project maven **POM.xml** add bellow dependencies:

```
  <!-- API -->
  <dependency>
    <groupId>org.inspector4j</groupId>
    <artifactId>api</artifactId>
    <version>3.1.0-RELEASE</version>
  </dependency>

  <!-- IMPLEMENTATION -->
  <dependency>
    <groupId>org.inspector4j</groupId>
    <artifactId>impl</artifactId>
    <version>2.0.0-RELEASE</version>
  </dependency>
```

## Getting Started
Consider the bellow class which we intend to inspect the parameters.

```Java
package org.inspector4j.impl;

import org.inspector4j.Secret;
import org.inspector4j.impl.model.Address;

public class UserServiceImpl {

    public String create(String name, String surname, @Secret Address address) {
       ...
    }

}
```

Where **org.inspector4j.impl.model.Address** has the bellow content:

```Java
package org.inspector4j.impl.model;

import java.io.Serializable;

public class Address implements Serializable {

   private String avenue;
   private String number;

    public String getAvenue() {
        return avenue;
    }

    public void setAvenue(String avenue) {
        this.avenue = avenue;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
```
Consider also the file **inspector4j.xml** , with the bellow content, is located under  **src/main/resources**

```xml
<Configuration xmlns="http://inspector4j.org">
    <Root scope="ATTRIBUTE" override="false"/>
    <Inspector name="org.inspector4j.impl.RunnerImpl" scope="SECRET" override="false"/>
</Configuration>
```

Consider the bellow  Class , **RunnerImpl** . which is the main Class.

```Java
package org.inspector4j.impl;

import org.inspector4j.Inspector4J;
import org.inspector4j.api.InspectionResult;
import org.inspector4j.impl.model.Address;

import java.lang.reflect.Method;

public class RunnerImpl {

    public static void main(String[] args) throws Exception {

        Method method = UserServiceImpl.class.getMethod("create", String.class, String.class, Address.class);
        InspectionResult result = Inspector4J.get().inspect(method, new Object[]{"Delfim", "RaitonBL ", new Address("Av.25 de Setembro", "0001")});
        System.out.println(result.toMap()); // Line 1

        result = Inspector4J.get(RunnerImpl.class).inspect(method, new Object[]{"Delfim", "RaitonBL ", new Address("Av.25 de Setembro", "0001")});
        System.out.println(result.toMap()); // Line 2

    }

}

```
The above code print's the bellow :
*  **{ surname=RaitonBL , name=Delfim}**  
Where the  ***org.inspector4j.api.Inspector*** instance constructed through Inspector4J.get() ( configured with  **Root** tag from **inspector4j.xml** , which ignores secret's since scope is **ATTRIBUTE**).
*  **{ address={number=0001, avenue=Av.25 de Setembro}, surname=RaitonBL , name=Delfim}**  
Where the  ***org.inspector4j.api.Inspector*** instance constructed through Inspector4J.get(RunnerImpl.class) ( configured with  **Inspector** tag where name attribute has org.**inspector4j.impl.RunnerImpl** as value from **inspector4j.xml** , which includes secret's since scope is **SECRET**).


## Configuration

Inspector4J ships two (2) types of  ***org.inspector4j.api.Inspector*** , **Default** & **Specified**  , where **Default** applies to any ***org.inspector4j.api.Inspector*** instance which configuration **attribute's** aren't declared on the Configuration API  and **Specified** are any  ***org.inspector4j.api.Inspector*** instance which configuration is assigned to a specific name on the Configuration API.
Each ***org.inspector4j.api.Inspector*** supports two (2) configuration attribute's which are:
* **scope** - can be ATTRIBUTE (ignores secret inspection) OR SECRET (include secret inspection )  , by default the value is ATTRIBUTE. Any **java.reflect.Parameter** or **java.reflect.Field** or **java.reflect.Type** annotated with **@org.inspector4j.api.Secret** are considered secrets;
* **override** - Boolean which determines whether the scope can be override during a specific inspection. Whenever the value is **TRUE** override is supported during a **inspection** otherwise the override is ignored. This parameter is aligned with ***org.inspector4j.api.Inspector*** **InspectionResult inspect(java.reflect.Method,  Object[] , boolean)** methood.

The Configuration API combines configuration values from multiple providers, each known as **Configuration Provider**. Each **Configuration Provider** has a specified priority, defined by the API. The higher the priority the values taken from the **Configuration Provider** will override values from lower priority **Configuration Provider**.
The Configuration API ships five (5) **Configuration Provider's** ,  ordered by priority , which are:
* **Programatic Configuration Provider** ;
* **XML Configuration Provider** ;
* **Microprofile Configuration Provider**
* **System Configuration Provider**


### Programatic Configuration
The Programatic Configuration relies on **Configuration Manager** which can be accessed through **Inspector4J.getConfigurationManager()** where **ConfigurationManager setScope(org.inspector4j.Scope)**  and **ConfigurationManager setOverridable(Boolean)** applies only for **Default** ***org.inspector4j.api.Inspector*** , and the other's method's apply to **Specified** ***org.inspector4j.api.Inspector***

```Java
package org.inspector4j.impl;

import org.inspector4j.Inspector4J;
import org.inspector4j.api.InspectionResult;
import org.inspector4j.impl.model.Address;

import java.lang.reflect.Method;

public class RunnerImpl {

    public static void main(String[] args) throws Exception {
        // Configuration
        Inspector4J.getConfigurationManager().setScope(Scope.ATTRIBUTE).setOverridable(RunnerImpl.class,false); 

        Method method = UserServiceImpl.class.getMethod("create", String.class, String.class, Address.class);
        InspectionResult result = Inspector4J.get().inspect(method, new Object[]{"Delfim", "RaitonBL ", new Address("Av.25 de Setembro", "0001")});
        System.out.println(result.toMap()); // Line 1

        result = Inspector4J.get(RunnerImpl.class).inspect(method, new Object[]{"Delfim", "RaitonBL ", new Address("Av.25 de Setembro", "0001")});
        System.out.println(result.toMap()); // Line 2

    }

}
```
The Line bellow the Configuration comment represents the Programatic Configuration that matches the **inspector4j.xml** shown on the Getting Started example.

### XML Configuration
INSPECTOR4J XML Configuration is activated whenever  **inspector4j.xml** is found within **src/resources** . The xml structure is composed by an unique **Root** , element which configures **Default** ***org.inspector4j.api.Inspector***  , and repeatable **Inspector** , element which configures **Specified**  ***org.inspector4j.api.Inspector***
```xml
<Configuration xmlns="http://inspector4j.org">
    <Root scope="ATTRIBUTE" override="false"/>
    <Inspector name="org.inspector4j.impl.InspectorTests" scope="SECRET" override="false"/>
    <Inspector name="org.inspector4j.impl.RunnableImpl" scope="ATTRIBUTE" override="false"/>
</Configuration>
```
The XML Configuration supports values interpolation where for example **${sys:override}** will be replaced with **System.getProperty("override")** and **${env:override}** will be replaced with **System.getEnv("override")**. This interpolation can be expanded to **${override}** where in case **System.getEnv("override")** is **null** **System.getProperty("override")** **override** will be used.
```xml
<Configuration xmlns="http://inspector4j.org">
    <Root scope="ATTRIBUTE" override="false"/>
    <Inspector name="org.inspector4j.impl.InspectorTests" scope="${env:TEST_SCOPE}" override="${env:TEST_OVERRIDE}"/>
    <Inspector name="org.inspector4j.impl.RunnableImpl" scope="${env:RUNNABLE_SCOPE}" override="${env:RUNNABLE_SCOPE}"/>
</Configuration>
```

The code above represents the **inspector4j.xml** configuration structure. The **Root** element represents the default configuration that will apply to all Inspector instances not declared in the configuration file , and
The Inspector element , which is repeatable , specifies the configuration to be applied to a specific Inspector whose **name** attribute matches the name one defined.
The **Root** & **Inspector** element's contains two attributes which are:  

**scope** - can be ATTRIBUTE (ignores secret inspection) OR SECRET (include secret inspection )  , by default the value is ATTRIBUTE. Any **java.reflect.Parameter** or **java.reflect.Field** or **java.reflect.Type** annotated with **@org.inspector4j.api.Secret** are considered secrets;

**override** - Determines whether **scope** is overridable , by default is false. In case **org.inspector4j.Inspector** method **public InspectionResult inspect(Method method, Object[] args, boolean inspectAll)** with **inspectAll** is **true** and this configuration is **false** meanwhile **scope** configuration is **ATTRIBUTE** secrets will not be inspected.

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