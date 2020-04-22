# springboot2.0深度实践之核心技术篇

## 第一章：系列总览

### 1.1 课程导学

![Screen Shot 2020-03-30 at 8.05.48 AM](Screen%20Shot%202020-03-30%20at%208.05.48%20AM.png)

2. 四个问题
   1. Spring Boot是如何基于Spring Famework逐步走向自动装配
   2. springApplication是怎样掌控Spring 应用生命周期？
   3. springboot外部化配置与Spring Environment抽象之间是什么关系
   4. Spring Web MVC向Spring Reactive WebFlux过度的真实价值和意义
3. 五种原则
   1. 场景分析，掌握技术选型
   2. 系统学习，拒绝浅尝辄止
   3. 重视规范，了解发展趋势
   4. 源码解读，理解设计思想
   5. 实战演练，巩固学习成果
4. 课程收获
   1. spring全栈技术和实现原理
   2. spring boot核心技术
   3. BAT大规模微服务基础设施开发与生产实施经验
5. 建议
   1. 多记api，多写代码，对以后的职业发展很有作用。



### 1.2 为什么说spring2.0易学难精

1. spring易学
   1. 组件自动装配：规约大于配置，专注核心业务
   2. 外部化配置：以此构建，按需调配，导出运行
   3. 嵌入式容器：内置容器、无需部署、独立运行
   4. spring Boot Starter：简化依赖，按需装配、自我包含
   5. Production-Ready: 一站式运维、生态无缝整合
   
2. spring boot 难精
   1. 组件自动装配：模式注解、@Enable模块、条件装配、加载机制
   2. 外部化配置： Environment抽象、生命周期、破坏性变更
   3. 嵌入式容器：Servlet Web 容器、Reactive Web容器
   4. Spring boot Starter:依赖管理、装配条件、装配顺序
   5. Production-Ready: 健康检查、数据指标、@Endpoint管控
   
3. Spring Boot 与Java EE 规范
   1. Web：Servlet(JSR-315、JSR-340)
   2. SQL:JDBC(JSR=221)
   3. 数据校验：Bean Validation(JSR 303、jSR-349)
   4. 缓存：Java Caching Api(JSR-107)
   5. WebSockets：Java API for WebSocket(JSR-356)
   6. WEB services：JAX-WS(JSR-224
   7. java 管理：JMX（JSR 3）
   8. 消息:JMS(JSR-914)

   ### 1.3 系列总览

   1. 核心特性
   2. web应用
   3. 数据相关：jdbc jpa
   4. 功能扩展：api特性
   5. 运维管理

   ### 1.4 核心特性

   1. 组件自动装配
      1. 激活：@EnableAutoConfiguration
      2. 配置: /META__inf/spring.factories
      3. 实现：XXXAutoConfiguration
   2. 嵌入式web容器：Tomcat、jetty、Undertow
      1. Web Servlet: Tomcat、Jetty 和Undertow
      2. Web Reactive：Netty Web Server
   3. 生产准备特性：指标、健康检查、外部化配置
      1. 指标：/actuator/metrics
      2. 健康建厂：/actuator/health
      3. 外部化配置：/actuator/configprops

   ### 1.6 Web应用

   Demo location:https://github.com/ericzhang08/Laboratory/tree/master/spring-boot-demos/dive-in-spring-boot

   

   #### 1.6.1传统Servlet应用

   1. servlet组件：Servlet Filter Listener

      1. Servlet

         1. 实现

            @WebServlet(url)
            HttpServlet

         2. 注册
            URL 映射
            @WebServlet(urlPatterns = "/my/servlet")

         3. 注册
            @ServletComponentScan(basePackages = "com.imooc.diveinspringboot.web.servlet")

         ```
         @WebServlet(urlPatterns = "/my/servlet")
         public class MyServlet extends HttpServlet {
             @Override
             protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                 resp.getWriter().println("Hello, world");
             }
         }
         ```

         ```
         @SpringBootApplication
         @ServletComponentScan(basePackages = "com.ericzhang08.diveinspringboot.web.servlet")
         public class DiveInSpringBootApplication {
         
             public static void main(String[] args) {
                 SpringApplication.run(DiveInSpringBootApplication.class, args);
             }
         
         }
         ```

         

   2. Servlet注册：

      1. Servlet注解 
         - @ServletComponentScan+
           - @WebServlet
           - @WebFilter
           - @WebListener
      2. Spring Bean
      3. RegistrationBean

   3. 异步非阻塞：

      1. 异步Servlet、
      2. 非阻塞Servlet

   #### 1.6.2 Spring Web Mvc应用

   1. Web MVC视图：模板引擎、内容协商、异常处理
      1. 视图
         1. ViewResolver
         2. View
      2. 模板引擎
         1. Thymeleaf
         2. Freemarker
         3. JSP
      3. 内容协商
         1. ContentNegotiationConfigurer
         2. ContentMegotiationStrategy
         3. ContentNegotiatingViewResolver
      4. 异常处理
         1. @ExceptionHandler
         2. HandlerExceptionResolver
            1. ExceptionHandlerExceptionResolver
         3. BasicErrorController(Spring boot)
   2. Web MVC REST:资源服务、资源跨域、服务发现
      1. 资源服务
         1. @RequestMapping
            1. GetMapping
         2. @ResponseBody
         3. @RequestBody
      2. 资源跨域
         1. CrossOrigin
         2. WebMvcConfigurer#addCorsMappings
         3. 传统解决方案
            1. IFrame
            2. JSONP
      3. 服务发现
         1. Hateos
   3. Web MVC核心：核心架构、处理流程、核心组件
      1. 核心结构
      2. 处理流程
      3. 核心组件
         1. DispatcherServlet
         2. HandleMapping
         3. HandlerAdapter
         4. ViewResolver

   

   #### 1.6.3 Web Fluxy应用

   对servlet的补充

   1. Reactor基础：Java Lambda、Mono、Flux
   2. Web Flux核心：Web Mvc注解、函数式声明、异步非阻塞
      1. 注解兼容
         1. @Controller
         2. @RequestMapping
         3. @RequestBody
      2. 函数式声明
         1. @RouterFunction
      3. 异步非阻塞
         1. Servlet 3.1+
         2. Netty Reactor
   3. 使用场景：Web Flux的优势和类型是
      1. 页面渲染
      2. Rest应用
      3. 性能测试
         1. http://blog.ippon.tech/spring-5-webflux-performance-tests/

   #### 1.6.4 web Server应用

   1. 切换Web Server

      1. 切换jetty

         1. ```
            <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
            <!-- Exclude the Tomcat dependency -->
            <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
            </exclusions>
            </dependency>
            <!-- Use Jetty instead -->
            <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jetty</artifactId>
            </dependency
            ```

            

      2. 切换web flux

         1. ```
            <!--<dependency>-->
            <!--<groupId>org.springframework.boot</groupId>-->
            <!--<artifactId>spring-boot-starter-web</artifactId>-->
            <!--<exclusions>-->
            <!--&lt;!&ndash; Exclude the Tomcat dependency &ndash;&gt;-->
            <!--<exclusion>-->自定义 Servlet Web Server
            WebServerFactoryCustomizer
            自定义 Reactive Web Server
            ReactiveWebServerFactoryCustomizer
            数据相关
            关系型数据
            JDBC
            依赖
            <!--<groupId>org.springframework.boot</groupId>-->
            <!--<artifactId>spring-boot-starter-tomcat</artifactId>-->
            <!--</exclusion>-->
            <!--</exclusions>-->
            <!--</dependency>-->
            <!--&lt;!&ndash; Use Jetty instead &ndash;&gt;-->
            <!--<dependency>-->
            <!--<groupId>org.springframework.boot</groupId>-->
            <!--<artifactId>spring-boot-starter-jetty</artifactId>-->
            <!--</dependency>-->
            <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
            </dependency>
            ```

            

   2. 自定义Servlet Web Server

      1. WebServerFactoryCustomizer

   3. 自定义Reactive Web Server

      1. ReactiveWebServerFactoryCustomizer

   ### 1.7 数据相关

   #### 1.7.1 关系型数据

   1. jdbc：数据源、jdbcTemplate、自动装配

      1. 依赖

         ```
         <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-jdbc</artifactId>
         </dependency>
         ```

      2. 数据源接口

         1. javax.sql.DataSource  java定义的

      3. jdbctemplate ： jdbc部分定义

   2. JPA：实体映射关系、实体操作、自动装配

      1. 依赖,依赖jdbc中的datasource

         ```
         <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-data-jpa</artifactId>
         </dependency>
         ```

      2. 实体映射关系：表的映射关系

      3. 实体操作：javax.persistence.EntityManager

      4. 自动装配：HibernateJpaAutoConfiguration

   3. 事务：Spring 事务抽象、JDBC事务处理、自动装配

      1. 依赖

         ```
         <dependency>
         <groupId>org.springframework</groupId>
         <artifactId>spring-tx</artifactId>
         </dependency>
         ```

      2. Spring事务抽象：PlatformTransactionManager:根接口

      3. JDBC事务处理：DataSourceTransactionManager

         1. 

      4. 自动装配:

   

   ### 1.8 功能扩展

   #### spring Boot应用

   1. SpringApplication:失败分析、应用特性、事件监听
      1. 失败分析：FailureAnalysisReporter
   2. 应用特性
      1. SpringApplication Fuent Api
   3. spring Boot配置、profile、配置属性
      1. 外部化配置
         1. ConfigurationProperty
      2. @profile
      3. 配置属性
         1. PropertySources
   4. Spring boot Starter：Starter开发、最佳实践

   

   ### 1.9运维管理

   #### Spring Boot Actuator

   1. 端点：各类Web和JMX Endpoints
      1. Web Endpoints
      2. JMX endpoints
   2. 健康检查:health、HealthIndicator
   3. 指标:内建Metrics、自定义Metrics

   ## 2.走向自动装配

### 2.1  Spring Framework手动装配

#### 2.1.1 spring模式注解装配

定义：一种用于生命在应用中扮演“组件”角色的注解

模式注解是一种用于声明在应用中扮演“组件”角色的注解。如 Spring Framework 中的 @Repository 标注在任何类上 ，用于扮演仓储角色的模式注解。
@Component 作为一种由 Spring 容器托管的通用模式组件，任何被 @Component 标准的组件均为组件扫描的候选对象。类似地，凡是被 @Component 元标注（meta-annotated）的注解，如 @Service ，当任何组件标注它时，也被视作组件扫描的候选对象

举例：@Component、@Service、@Configuration

装配：<context:Component-scan>或@ComponentScan

##### 模式注解举例

| Spring Framework 注解 |                  |      |
| --------------------- | ---------------- | ---- |
| @Repository           | 数据仓储模式注解 |      |
| @Component            | 通用组件模式注解 |      |
| @Service              | 服务模式注解     |      |
| @Controller Web       | 控制器模式注解   |      |
| @Configuration        | 配置类模式注解   |      |

#####  模式注解的作用

##### 注解装配方式

1. <context:component-scan> 方式

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"@ComponentScan 方式
   自定义模式注解
   @Component “派生性”
   @Component
   @Repository
   FirstLevelRepository
   xmlns:context="http://www.springframework.org/schema/context"
   xsi:schemaLocation="http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans.xsd
   http://www.springframework.org/schema/context http://www.springframework.org/schema/context/springcontext.xsd">
   <!-- 激活注解驱动特性 -->
   <context:annotation-config />
   <!-- 找寻被 @Component 或者其派生 Annotation 标记的类（Class），将它们注册为 Spring Bean -->
   <context:component-scan base-package="com.imooc.dive.in.spring.boot" />
   </beans>
   ```

2. @ComponentScan 方式

   ```java
   @ComponentScan(basePackages = "com.imooc.dive.in.spring.boot")
   public class SpringConfiguration {
   ...
   }
   ```

3. 使用模式注解的方法

   1. ```
      @FirstLevelRepository(value = "myFirstLevelRepository")
      public class MyFirstLevelRepository {
      }
      ```

   2. ```
      @ServletComponentScan(basePackages = "com.ericzhang08.diveinspringboot.web.servlet")
      public class DiveInSpringBootApplication {
      ```



##### 自定义注解

1. @component 派生性

   1. ```
      /
      **
      * 一级 {@link Repository @Repository}
      * *
      @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
      * @since 1.0.0
      */
      @Target({ElementType.TYPE})
      @Retention(RetentionPolicy.RUNTIME)
      @Documented
      @Repository
      public @interface FirstLevelRepository {
      String value() default "";
      }
      ```

      顶层注解和下层注解没有什么区别，相当于继承，但是java的注解编程规范中是没有继承的

      被@Comonent注解的注解注解别的类，和@Component注解作用相同

2. @Component 层次性

   ```
   @Target({ElementType.TYPE})
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   @FirstLevelRepository
   public @interface SecondLevelRepository {
   String value() default "";
   }
   ```

   被@Comonent注解的注解注解别的注解，注解别的类和Component直接注解作用相同ß

### 2.2 Springboot @Enable模块装配

​	定义：具备相同领域的功能组件集合，组合所形成的一个独立的单元.Spring Framework3.1开始支持 @""

举例：@EnableWebMvc\@EnableAutoConfiguration等

实现：注解方式，编程方式

![Screen Shot 2020-04-14 at 10.38.38 PM](Screen%20Shot%202020-04-14%20at%2010.38.38%20PM.png)

##### 实现方式

1. 注解驱动方式

   ```
   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.TYPE)
   @Documented
   @Import(DelegatingWebMvcConfiguration.class)
   public @interface EnableWebMvc {
   }
   ```

   ```
   @Configuration
   public class DelegatingWebMvcConfiguration extends
   WebMvcConfigurationSupport {
   ...
   }
   ```

   

2. 接口编程方式

   ```
   @
   Target(ElementType.TYPE)
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   @Import(CachingConfigurationSelector.class)
   public @interface EnableCaching {
   ...
   }
   ```

   ```
   public class CachingConfigurationSelector extends AdviceModeImportSelector<EnableCaching> {
   /**
   * {@inheritDoc}
   * @return {@link ProxyCachingConfiguration} or {@code
   AspectJCacheConfiguration} for
   * {@code PROXY} and {@code ASPECTJ} values of {@link
   EnableCaching#mode()}, respectively
   */
   public String[] selectImports(AdviceMode adviceMode) {
   switch (adviceMode) {
   case PROXY:
   return new String[] {
   AutoProxyRegistrar.class.getName(),ProxyCachingConfiguration.class.getName() };
   case ASPECTJ:
   return new String[] {
   AnnotationConfigUtils.CACHE_ASPECT_CONFIGURATION_CLASS_NAME };
   default:
   return null;
   }
   }
   ```

   

### 2.2  Spring boot的自动装配

### 2.3 总结