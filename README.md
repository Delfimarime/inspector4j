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
    <version>3.1.1-RELEASE</version>
  </dependency>

  <!-- IMPLEMENTATION -->
  <dependency>
    <groupId>org.inspector4j</groupId>
    <artifactId>impl</artifactId>
    <version>2.0.1-RELEASE</version>
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
import org.inspector4j.Secret;

public class Address implements Serializable {

   private String avenue;
   @Secret
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
    <Root secrets-visibility="MASKED" vallow-runtime-config="false"/>
    <Inspector name="org.inspector4j.impl.RunnerImpl" secrets-visibility="VISIBLE" allow-runtime-config="false"/>
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
*  **{  adress={&#42;&#42;} surname=RaitonBL , name=Delfim}**  
Where the  ***org.inspector4j.api.Inspector*** instance constructed through Inspector4J.get() ( configured with  **Root** tag from **inspector4j.xml** , which ignores secret's since secret-visibility is **MASKED**).
*  **{ address={number=0001, avenue=Av.25 de Setembro}, surname=RaitonBL , name=Delfim}**  
Where the  ***org.inspector4j.api.Inspector*** instance constructed through Inspector4J.get(RunnerImpl.class) ( configured with  **Inspector** tag where name attribute has org.**inspector4j.impl.RunnerImpl** as value from **inspector4j.xml** , which includes secret's since secret-visibility is **VISIBLE**).


## Configuration

Inspector4J ships two (2) types of  ***org.inspector4j.api.Inspector*** , **Default** & **Specified**  , where **Default** applies to any ***org.inspector4j.api.Inspector*** instance which configuration **attribute's** aren't declared on the Configuration API  and **Specified** are any  ***org.inspector4j.api.Inspector*** instance which configuration is assigned to a specific name on the Configuration API.
Each ***org.inspector4j.api.Inspector*** supports two (2) configuration attribute's which are:
* **secrets-visibility** - can be **MASKED** (ignores secret inspection and prints {&#42;&#42;} for object's or &#42;&#42; for basic types ) OR **VISIBLE** (inspect's everything including secrets)  , by default the value is **MASKED**. Any **java.reflect.Parameter** or **java.reflect.Field** or **java.reflect.Type** annotated with **@org.inspector4j.api.Secret** are considered secrets;
* **allow-runtime-config** - Boolean which determines whether the **secret-visibility** attribute can be overrided during a specific inspection. Whenever the value is **TRUE** override is supported during a **inspection** otherwise the override is ignored. This parameter is important in order to invoke ***org.inspector4j.api.Inspector*** **InspectionResult inspect(java.reflect.Method,  Object[] , boolean)**.

The Configuration API combines configuration values from multiple providers, each known as **Configuration Provider**. Each **Configuration Provider** has a specified priority, defined by the API. The higher the priority the values taken from the **Configuration Provider** will override values from lower priority **Configuration Provider**.
The Configuration API ships four (4) **Configuration Provider's** ,  ordered by priority , which are:
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
        Inspector4J.getConfigurationManager().setSecretVisibility(SecretVisibility.MASKED).setAllowRuntimeConfiguration(RunnerImpl.class,false); 

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
    <Root secrets-visibility="MASKED" allow-runtime-config="false"/>
    <Inspector name="org.inspector4j.impl.InspectorTests" secrets-visibility="VISIBLE" allow-runtime-config-="false"/>
    <Inspector name="org.inspector4j.impl.RunnableImpl" secrets-visibility="MASKED" allow-runtime-config="false"/>
</Configuration>
```
The XML Configuration supports values interpolation where for example **${sys:override}** will be replaced with **System.getProperty("override")** and **${env:override}** will be replaced with **System.getEnv("override")**. This interpolation can be expanded to **${override}** where in case **System.getEnv("override")** is **null** **System.getProperty("override")** **override** will be used.
```xml
<Configuration xmlns="http://inspector4j.org">
    <Root secrets-visibility="MASKED" allow-runtime-config="false"/>
    <Inspector name="org.inspector4j.impl.InspectorTests" secrets-visibility="${env:TEST_VISIBILITY}" allow-runtime-config="${env:TEST_ALLOW}"/>
    <Inspector name="org.inspector4j.impl.RunnableImpl" secrets-visibility="${env:RUNNABLE_VISIBILITY}" allow-runtime-config="${env:RUNNABLE_ALLOW}"/>
</Configuration>
```

### Microprofile Configuration
INSPECTOR4J Microprofile Configuration is activated whenever  Microprofile is detected within the **classpath** . The microprofile configuration inspect the Microprofile configuration properties for keys that match **org.inspect4j.&#42;.secrets-visibility* and **org.inspect4j.&#42;.allow-runtime-config** where &#42; should be replaced with the name associated to the **Specified**  ***org.inspector4j.api.Inspector*** . The **Default** ***org.inspector4j.api.Inspector*** can be configured through  **org.inspect4j.secrets-visibility* and **org.inspect4j.allow-runtime-config**


### System Configuration
INSPECTOR4J System Configuration inspect's **Environment Properties** and **System Properties** configuration properties for keys that match **org.inspect4j.&#42;.secrets-visibility* and **org.inspect4j.&#42;.allow-runtime-config** where &#42; should be replaced with the name associated to the **Specified**  ***org.inspector4j.api.Inspector*** . The **Default** ***org.inspector4j.api.Inspector*** can be configured through  **org.inspect4j.secrets-visibility* and **org.inspect4j.allow-runtime-config**.
The inspection search's the property on the **Environment Variable** and in case the property is absent on the **Environment Variable** then **System Variables** is inspected.

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