## Core Technologies

### The IoC Container

### 1.1. Introduction to the Spring IoC Container and Beans

 ioc：It is a process whereby objects define their dependencies (that is, the other objects they work with) only through constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is constructed or returned from a factory method. The container then injects those dependencies when it creates the bean. 

1. The `org.springframework.beans` and `org.springframework.context` packages are the basis for Spring Framework’s IoC container. The [`BeanFactory`](https://docs.spring.io/spring-framework/docs/5.3.0/javadoc-api/org/springframework/beans/factory/BeanFactory.html) interface provides an advanced configuration mechanism capable of managing any type of object. [`ApplicationContext`](https://docs.spring.io/spring-framework/docs/5.3.0/javadoc-api/org/springframework/context/ApplicationContext.html) is a sub-interface of `BeanFactory`. It adds:
   - Easier integration with Spring’s AOP features
   - Message resource handling (for use in internationalization)
   - Event publication
   - Application-layer specific contexts such as the `WebApplicationContext` for use in web applications.
   - 

### 1.2. Container Overview

The `org.springframework.context.ApplicationContext` interface represents the Spring IoC container and is responsible for instantiating, configuring, and assembling the beans.



The container gets its instructions on what objects to instantiate, configure, and assemble by reading configuration metadata. The configuration metadata is represented in XML, Java annotations, or Java code. 

![container magic](container-magic.png)

#### 1.2.1. Configuration Metadata

1. annotation-based configuration

2.  XML-based metadata

   1. XML-based configuration metadata configures these beans as `` elements inside a top-level `` element

   2. ```
      <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
              https://www.springframework.org/schema/beans/spring-beans.xsd">
      
          <bean id="..." class="...">  
              <!-- collaborators and configuration for this bean go here -->
          </bean>
      
          <bean id="..." class="...">
              <!-- collaborators and configuration for this bean go here -->
          </bean>
      
          <!-- more bean definitions go here -->
      
      </beans>
      ```

      

3. Java-based configuration

   1.  Java configuration typically uses `@Bean`-annotated methods within a `@Configuration` class.

   

   ### 1.3. Bean Overview

   Within the container itself, these bean definitions are represented as `BeanDefinition` objects, which contain (among other information) the following metadata:

   - A package-qualified class name: typically, the actual implementation class of the bean being defined.

   - Bean behavioral configuration elements, which state how the bean should behave in the container (scope, lifecycle callbacks, and so forth).

   - References to other beans that are needed for the bean to do its work. These references are also called collaborators or dependencies.

   - Other configuration settings to set in the newly created object — for example, the size limit of the pool or the number of connections to use in a bean that manages a connection pool.

   - This metadata translates to a set of properties that make up each bean definition. The following table describes these properties

     | Property                 | Explained in…                                                |
     | :----------------------- | :----------------------------------------------------------- |
     | Class                    | [Instantiating Beans](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-class) |
     | Name                     | [Naming Beans](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-beanname) |
     | Scope                    | [Bean Scopes](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes) |
     | Constructor arguments    | [Dependency Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-collaborators) |
     | Properties               | [Dependency Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-collaborators) |
     | Autowiring mode          | [Autowiring Collaborators](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-autowire) |
     | Lazy initialization mode | [Lazy-initialized Beans](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-lazy-init) |
     | Initialization method    | [Initialization Callbacks](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-lifecycle-initializingbean) |
     | Destruction method       | [Destruction Callbacks](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-lifecycle-disposablebean) |

   

#### 1.3.1. Naming Beans

The convention is to use the standard Java convention for instance field names when naming beans. That is, bean names start with a lowercase letter and are camel-cased from there. Examples of such names include `accountManager`, `accountService`, `userDao`, `loginController`, and so forth.

#### 1.3.2. Instantiating Beans

##### Instantiation with a Constructor

```
<bean id="exampleBean" class="examples.ExampleBean"/>

<bean name="anotherExample" class="examples.ExampleBeanTwo"/>
```



##### Instantiation with a Static Factory Method

```
<bean id="clientService"
    class="examples.ClientService"
    factory-method="createInstance"/>
```

```
public class ClientService {
    private static ClientService clientService = new ClientService();
    private ClientService() {}

    public static ClientService createInstance() {
        return clientService;
    }
}
```



##### instantiation by Using an Instance Factory Method

```
<!-- the factory bean, which contains a method called createInstance() -->
<bean id="serviceLocator" class="examples.DefaultServiceLocator">
    <!-- inject any dependencies required by this locator bean -->
</bean>

<!-- the bean to be created via the factory bean -->
<bean id="clientService"
    factory-bean="serviceLocator"
    factory-method="createClientServiceInstance"/>
```

```
public class DefaultServiceLocator {

    private static ClientService clientService = new ClientServiceImpl();

    public ClientService createClientServiceInstance() {
        return clientService;
    }
}
```

###  1.4. Dependencies

Dependency injection (DI) is a process whereby objects define their dependencies (that is, the other objects with which they work) only through constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is constructed or returned from a factory method. The container then injects those dependencies when it creates the bean.



This process is fundamentally the inverse (hence the name, Inversion of Control) of the bean itself controlling the instantiation or location of its dependencies on its own by using direct construction of classes or the Service Locator pattern.



##### why di?

1. Code is cleaner with the DI principle, and 

2. decoupling is more effective when objects are provided with their dependencies. The object does not look up its dependencies and does not know the location or class of the dependencies



#### DI exists in two major variants?

##### Constructor-based Dependency Injection

Constructor argument resolution matching occurs by using the argument’s type. If no potential ambiguity exists in the constructor arguments of a bean definition, the order in which the constructor arguments are defined in a bean definition is the order in which those arguments are supplied to the appropriate constructor when the bean is being instantiated.

```
<beans>
    <bean id="beanOne" class="x.y.ThingOne">
        <constructor-arg ref="beanTwo"/>
        <constructor-arg ref="beanThree"/>
    </bean>

    <bean id="beanTwo" class="x.y.ThingTwo"/>

    <bean id="beanThree" class="x.y.ThingThree"/>
</beans>
```



##### Constructor Argument Resolution

1. Constructor argument type matching

   ```
   <bean id="exampleBean" class="examples.ExampleBean">
       <constructor-arg type="int" value="7500000"/>
       <constructor-arg type="java.lang.String" value="42"/>
   </bean>
   ```

   

2. **Constructor argument index**

   ```
   <bean id="exampleBean" class="examples.ExampleBean">
       <constructor-arg index="0" value="7500000"/>
       <constructor-arg index="1" value="42"/>
   </bean>
   ```

3. Constructor argument name

   ```
   <bean id="exampleBean" class="examples.ExampleBean">
       <constructor-arg name="years" value="7500000"/>
       <constructor-arg name="ultimateAnswer" value="42"/>
   </bean>
   ```



##### Setter-based Dependency Injection

Setter-based DI is accomplished by the container calling setter methods on your beans after invoking a no-argument constructor or a no-argument `static` factory method to instantiate your bean.

```
public class SimpleMovieLister {

    // the SimpleMovieLister has a dependency on the MovieFinder
    private MovieFinder movieFinder;

    // a setter method so that the Spring container can inject a MovieFinder
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    // business logic that actually uses the injected MovieFinder is omitted...
}
```

##### Constructor-based or setter-based DI?

Since you can mix constructor-based and setter-based DI, it is a good rule of thumb to use constructors for mandatory dependencies and setter methods or configuration methods for optional dependencies.

1. it lets you implement application components as immutable objects and 
2. ensures that required dependencies are not `null`. , constructor-injected components are always returned to the client (calling) code in a fully initialized state. 
3. when dealing with third-party classes for which you do not have the source, the choice is made for you.
4. Setter injection should primarily only be used for optional dependencies that can be assigned reasonable default values within the class.One benefit of setter injection is that setter methods make objects of that class amenable to reconfiguration or re-injection later.



##### Dependency Resolution Process

- The `ApplicationContext` is created and initialized with configuration metadata that describes all the beans. Configuration metadata can be specified by XML, Java code, or annotations.
- For each bean, its dependencies are expressed in the form of properties, constructor arguments, or arguments to the static-factory method (if you use that instead of a normal constructor). These dependencies are provided to the bean, when the bean is actually created.
- Each property or constructor argument is an actual definition of the value to set, or a reference to another bean in the container.
- Each property or constructor argument that is a value is converted from its specified format to the actual type of that property or constructor argument. By default, Spring can convert a value supplied in string format to all built-in types, such as `int`, `long`, `String`, `boolean`, and so forth.





By some means the group decides on a Kata to work on,perhapsthe [DojoOrganizer](https://codingdojo.org/DojoOrganizer) suggests one. No-one need have prepared anything much before the meeting.