# spring

1    Spring概述

①Spring是一个开源框架

②Spring为简化企业级开发而生，使用Spring，JavaBean就可以实现很多以前要靠EJB才能实现的功能。同样的功能，在EJB中要通过繁琐的配置和复杂的代码才能够实现，而在Spring中却非常的优雅和简洁。

③Spring是一个**IOC**(DI)和**AOP**容器框架。

④Spring的优良特性

[1]**非侵入式**：基于Spring开发的应用中的对象可以不依赖于Spring的API

[2]**依赖注入**：DI——Dependency Injection，反转控制(IOC)最经典的实现。

[3]**面向切面编程**：Aspect Oriented Programming——AOP

[4]**容器**：Spring是一个容器，因为它包含并且管理应用对象的生命周期

​    [5]**组件化**：Spring实现了使用简单的组件配置组合成一个复杂的应用。在 Spring 中可以使用XML和Java注解组合这些对象。

​    [6]**一站式**：在IOC和AOP的基础上可以整合各种企业应用的开源框架和优秀的第三方类库（实际上Spring 自身也提供了表述层的SpringMVC和持久层的Spring JDBC）。

2. Spring 模块划分

   1. core

      1. spring-beans-4.0.0.RELEASE.jar

         spring-context-4.0.0.RELEASE.jar

         spring-core-4.0.0.RELEASE.jar

         spring-expression-4.0.0.RELEASE.jar

   2. aop +aspect : 面向切面

      1. spring-aop-4.0.0.RELEASE.jar

         spring-aspect-4.0.0.RELEASE.jar

   3. Test

      1. spring-test-4.0.0.RELEASE.jar

   4. 数据访问 & integration

      1. spring-jdbc-4.0.0.RELEASE.jar

         spring-orm-4.0.0.RELEASE.jar

         spring-ox(xml)m-4.0.0.RELEASE.jar

         spring-jms.-4.0.0.RELEASE.jar

         spring-tx-4.0.0.RELEASE.jar

   5. web

      1. spring-websocket-4.0.0.RELEASE.jar

         spring-web-4.0.0.RELEASE.jar  原生web

         spring-webmvc-4.0.0.RELEASE.jar 开发web项目的

         spring-webflux-4.0.0.RELEASE.jar

3.  如何创建spring项目

   ①使用springboot创建项目

   ②根据需要创建Spring配置文件

   ```
   	<!-- 使用bean元素定义一个由IOC容器创建的对象 -->
   	<!-- class属性指定用于创建bean的全类名 -->
   	<!-- id属性指定用于引用bean实例的标识 -->
   	<bean id="student" class="com.atguigu.helloworld.bean.Student">
   		<!-- 使用property子元素为bean的属性赋值 -->
   		<property name="studentId" value="1001"/>
   		<property name="stuName" value="Tom2015"/>
   		<property name="age" value="20"/>
   	</bean>
   
   ```

   3. 通过Spring的ioc容器创建Student类实例

      ```
      //1.创建IOC容器对象
      ApplicationContext iocContainer = 
      		new ClassPathXmlApplicationContext("helloworld.xml");
      
      //2.根据id值获取bean实例对象
      Student student = (Student) iocContainer.getBean("student");
      
      //3.打印bean
      System.out.println(student);
      
      ```

      

## ioc

①IOC(**Inversion of Control**)：**反转控制**。

在应用程序中的组件需要获取资源时，传统的方式是组件主动的从容器中获取所需要的资源，在这样的模式下开发人员往往需要知道在具体容器中特定资源的获取方式，增加了学习成本，同时降低了开发效率。

反转控制的思想完全颠覆了应用程序组件获取资源的传统方式：反转了资源的获取方向——改由容器主动的将资源推送给需要的组件，开发人员不需要知道容器是如何创建资源对象的，只需要提供接收资源的方式即可，极大的降低了学习成本，提高了开发的效率。这种行为也称为查找的被动形式。

控制资源的获取方式，

1. 主动式：自己创建
2. 被动式：资源的获取有容器创建。

容器：管理所有的组件；容器可以自动的探查出哪些组件需要用到另一些组件；容器自动帮助创建，并赋值。

②DI(**Dependency Injection**)：**依赖注入**。

IOC的另一种表述方式：即组件以一些预先定义好的方式(例如：setter 方法)接受来自于容器的资源注入。相对于IOC而言，这种表述更直接。



③IOC容器在Spring中的实现

1. 在通过IOC容器读取Bean的实例之前，需要先将IOC容器本身实例化。
2. Spring提供了IOC容器的两种实现方式
   1.  BeanFactory：IOC容器的基本实现，是Spring内部的基础设施，是面向Spring本身的，不是提供给开发人员使用的。
   2. ApplicationContext：BeanFactory的子接口，提供了更多高级特性。面向Spring的使用者，几乎所有场合都使用ApplicationContext而不是底层的BeanFactory。



④ApplicationContext的主要实现类

[1]ClassPathXmlApplicationContext：对应类路径下的XML格式的配置文件

[2]FileSystemXmlApplicationContext：对应文件系统中的XML格式的配置文件

![Screen Shot 2020-07-08 at 1.05.15 PM](Screen%20Shot%202020-07-08%20at%201.05.15%20PM.png)

[3]在初始化时就创建单例的bean，也可以通过配置的方式指定创建的Bean是多实例的。

 

⑤ConfigurableApplicationContext

[1]是ApplicationContext的子接口，包含一些扩展方法

[2]refresh()和close()让ApplicationContext具有启动、关闭和文的能力。 

 

⑥WebApplicationContext

专门为WEB应用而准备的，它允许从相对于WEB根目录的路径中完成初始化工作



### bean

#### 1.获取bean

##### 通过类型获取

从IOC容器中获取bean时，除了通过id值获取，还可以通过bean的类型获取。但如果同一个类型的bean在XML文件中配置了多个，则获取时会抛出异常，所以同一个类型的bean在容器中必须是唯一的。

```
  HelloWorld  helloWorld = cxt.getBean(HelloWorld. class);  
```

##### 通过id获取

```
  Person person = (Person) iocContainer.getBean("person01");

```



#### 2.给bean的属性赋值

##### 2.1赋值的途经

1. 通过bean的setXxx()方法赋值

   ```
   
       <bean id ="person01" class ="com.ericzhang08.helloworld.Person">
           <property name="lastName" value="zs"/>
           <property name="age" value="5"/>
           <property name="gender" value="female"/>
           <property name="email" value="aaa"/>
       </bean>
   ```

   

2. 通过bean的构造器赋值

   ```
        <bean id="book" class="com.atguigu.spring.bean.Book" >
              <constructor-arg value= "10010" index ="0"/>
              <constructor-arg value= "Book01" index ="1"/>
              <constructor-arg value= "Author01" index ="2"/>
              <constructor-arg value= "20.2" index ="3"/>
        </bean >
   ```

   

   1. 通过索引值指定参数位置

      ```
      
           <bean id="book" class="com.atguigu.spring.bean.Book" >
                 <constructor-arg value= "10010" index ="0"/>
                 <constructor-arg value= "Book01" index ="1"/>
                 <constructor-arg value= "Author01" index ="2"/>
                 <constructor-arg value= "20.2" index ="3"/>
           </bean >
      
      ```

      

   2. 通过类型不同区分重载的构造器

      ```
      <bean id="book" class="com.atguigu.spring.bean.Book" >
            <constructor-arg value= "10010" index ="0" type="java.lang.Integer" />
            <constructor-arg value= "Book01" index ="1" type="java.lang.String" />
            <constructor-arg value= "Author01" index ="2" type="java.lang.String" />
            <constructor-arg value= "20.2" index ="3" type="java.lang.Double" />
      </bean >
      
      ```

      

3. 给bean的级联属性赋值

   ```
        <bean id="action" class="com.atguigu.spring.ref.Action">
             <property name="service" ref="service"/>
             <!-- 设置级联属性(了解) -->
             <property name="service.dao.dataSource" value="DBCP"/>
        </bean>
   
   ```

   

4. p名称空间

   ```
   	id="studentSuper" 
   	class="com.atguigu.helloworld.bean.Student"
   	p:studentId="2002" p:stuName="Jerry2016" p:age="18" />
   
   ```

   

##### 2.2 可以使用的值

1. 字面量

   [1]可以使用字符串表示的值，可以通过value属性或value子节点的方式指定

   [2]基本数据类型及其封装类、String等类型都可以采取字面值注入的方式

   [3]若字面值中包含特殊字符，可以使用<![CDATA[]]>把字面值包裹起来

2. null值

   ```
              <property name= "bookName">
                  <null/>
              </property>
   
   ```

   

3. 外部已声明的bean

   ```
        <bean id="shop" class="com.atguigu.spring.bean.Shop" >
              <property name= "book" ref ="book"/>
        </bean >
   
   ```

   

4. 内部bean

   当bean实例仅仅给一个特定的属性使用时，可以将其声明为内部bean。内部bean声明直接包含在<property>或<constructor-arg>元素里，不需要设置任何id或name属性

   内部bean不能使用在任何其他地方

##### 2.3集合属性

1. 数组和list

   ```
        <bean id="shop" class="com.atguigu.spring.bean.Shop" >
                   <property name= "categoryList">
                  <!-- 以字面量为值的List集合 -->
                  <list>
                       <value> 历史</value >
                       <value> 军事</value >
                  </list>
              </property>
              <property name= "bookList">
                  <!-- 以bean的引用为值的List集合 -->
                  <list>
                       <ref bean= "book01"/>
                       <ref bean= "book02"/>
                  </list>
              </property>
        </bean >
   
   ```

2. map

   ```
   	<property name="bookMap">
   		<map>
   			<entry>
   				<key>
   					<value>bookKey01</value>
   				</key>
   				<ref bean="book01"/>
   			</entry>
   			<entry>
   				<key>
   					<value>bookKey02</value>
   				</key>
   				<ref bean="book02"/>
   			</entry>
   		</map>
   	</property>
   </bean>
   
   ```

3. properties

   ```
   <bean class="com.atguigu.spring.bean.DataSource" id="dataSource">
   	<property name="properties">
   		<props>
   			<prop key="userName">root</prop>
   			<prop key="password">root</prop>
   			<prop key="url">jdbc:mysql:///test</prop>
   			<prop key="driverClass">com.mysql.jdbc.Driver</prop>
   		</props>
   	</property>
   </bean>
   
   ```

### 通过工厂创建bean

#### 1. 静态工厂

调用静态工厂方法创建bean是将对象创建的过程封装到静态方法中。当客户端需要对象时，只需要简单地调用静态方法，而不用关心创建对象的细节。

声明通过静态方法创建的bean需要在bean的class属性里指定静态工厂类的全类名，同时在factory-method属性里指定工厂方法的名称。最后使用<constrctor-arg>元素为该方法传递方法参数。

```
    <bean id="static_person" class="com.ericzhang08.helloworld.StaticPersonFactory" factory-method="createPerson">
        <constructor-arg name="name" value="staticFactoryPerson"/>
    </bean>
    
```

```
    <bean id="instance_person" class="com.ericzhang08.helloworld.PersonStaticFactory" factory-bean="instance_person_factory" factory-method="createPerson">
        <constructor-arg name="name" value="instanceFactoryPerson"/>
    </bean>
```



#### 2. 实例工厂

实例工厂方法：将对象的创建过程封装到另外一个对象实例的方法里。当客户端需要请求对象时，只需要简单的调用该实例方法而不需要关心对象的创建细节。

```
public class PersonInstanceFactory {
    public Person createPerson(String name) {
        return new Person(name, 2, "", "");
    }
}
```



##### 实现方式

1. 配置工厂类实例的bean
2. 在factory-method属性里指定该工厂方法的名称
3. 使用 construtor-arg 元素为工厂方法传递方法参数



#### 3. Factory Bean

Spring中有两种类型的bean，一种是普通bean，另一种是工厂bean，即FactoryBean。

工厂bean跟普通bean不同，其返回的对象不是指定类的一个实例，其返回的是该工厂bean的getObject方法所返回的对象。

工厂bean必须实现org.springframework.beans.factory.FactoryBean接口。

```
    <bean id="factory-bean_person" class="com.ericzhang08.helloworld.PersonFactoryBean"/>


package com.ericzhang08.helloworld;

import org.springframework.beans.factory.FactoryBean;

public class PersonFactoryBean implements FactoryBean<Person> {
    @Override
    public Person getObject() {
        return new Person("factoryBean", 1, "", "");
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}

```



### bean的高级配置

#### 1. 配置信息的继承

```
	<property name="deptId" value="100"/>
	<property name="deptName" value="IT"/>
</bean>

<bean id="emp01" class="com.atguigu.parent.bean.Employee">
	<property name="empId" value="1001"/>
	<property name="empName" value="Tom"/>
	<property name="age" value="20"/>

	<!-- 重复的属性值 -->	
	<property name="detp" ref="dept"/>
</bean>

<bean id="emp02" class="com.atguigu.parent.bean.Employee">
	<property name="empId" value="1002"/>
	<property name="empName" value="Jerry"/>
	<property name="age" value="25"/>

	<!-- 重复的属性值 -->
	<property name="detp" ref="dept"/>
</bean>

<!-- 以emp01作为父bean，继承后可以省略公共属性值的配置 -->
<bean id="emp02" parent="emp01">
	<property name="empId" value="1002"/>
	<property name="empName" value="Jerry"/>
	<property name="age" value="25"/>
</bean>

```

Spring允许继承bean的配置，被继承的bean称为父bean。继承这个父bean的bean称为子bean

子bean从父bean中继承配置，包括bean的属性配置

​	子bean也可以覆盖从父bean继承过来的配置

**补充说明**
父bean可以作为配置模板，也可以作为bean实例。若只想把父bean作为模板，可以设置<bean>的abstract 属性为true，这样Spring将不会实例化这个bean
如果一个bean的class属性没有指定，则必须是抽象bean
并不是<bean>元素里的所有属性都会被继承。比如：autowire，abstract等。
也可以忽略父bean的class属性，让子bean指定自己的类，而共享相同的属性配置。但此时abstract必须设为true。



