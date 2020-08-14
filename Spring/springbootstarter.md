# spring-boot-starter-test

Spring Boot provides a number of utilities and annotations to help when testing your application. Test support is provided by two modules;

 `spring-boot-test` contains core items, and 

`spring-boot-test-autoconfigure` supports auto-configuration for tests.

Most developers will just use the `spring-boot-starter-test` ‘Starter’ which imports both Spring Boot test modules as well has JUnit, AssertJ, Hamcrest and a number of other useful libraries.

If you use the `spring-boot-starter-test` ‘Starter’ (in the `test` `scope`), you will find the following provided libraries:

- [JUnit](http://junit.org/) — The de-facto standard for unit testing Java applications.
- [Spring Test](https://docs.spring.io/spring/docs/4.3.11.RELEASE/spring-framework-reference/htmlsingle/#integration-testing) & Spring Boot Test — Utilities and integration test support for Spring Boot applications.
- [AssertJ](https://joel-costigliola.github.io/assertj/) — A fluent assertion library.
- [Hamcrest](http://hamcrest.org/JavaHamcrest/) — A library of matcher objects (also known as constraints or predicates).
- [Mockito](http://mockito.org/) — A Java mocking framework.
- [JSONassert](https://github.com/skyscreamer/JSONassert) — An assertion library for JSON.
- [JsonPath](https://github.com/jayway/JsonPath) — XPath for JSON.

## 1. Testing Spring applications

One of the major advantages of dependency injection is that it should make your code easier to unit test. You can simply instantiate objects using the `new` operator without even involving Spring. You can also use *mock objects* instead of real dependencies.

## 2. Testing Spring Boot applications

A Spring Boot application is just a Spring `ApplicationContext`, so nothing very special has to be done to test it beyond what you would normally do with a vanilla Spring context. One thing to watch out for though is that the external properties, logging and other features of Spring Boot are only installed in the context by default if you use `SpringApplication` to create it.

Spring Boot provides a `@SpringBootTest` annotation which can be used as an alternative to the standard `spring-test` `@ContextConfiguration` annotation when you need Spring Boot features. The annotation works by creating the `ApplicationContext` used in your tests via `SpringApplication`.

#### set springBootTest webEnvironment

You can use the `webEnvironment` attribute of `@SpringBootTest` to further refine how your tests will run:

- `MOCK` — Loads a `WebApplicationContext` and provides a mock servlet environment. Embedded servlet containers are not started when using this annotation. If servlet APIs are not on your classpath this mode will transparently fallback to creating a regular non-web `ApplicationContext`. Can be used in conjunction with `@AutoConfigureMockMvc` for `MockMvc`-based testing of your application.
- `RANDOM_PORT` — Loads an `EmbeddedWebApplicationContext` and provides a real servlet environment. Embedded servlet containers are started and listening on a random port.
- `DEFINED_PORT` — Loads an `EmbeddedWebApplicationContext` and provides a real servlet environment. Embedded servlet containers are started and listening on a defined port (i.e from your `application.properties` or on the default port `8080`).
- `NONE` — Loads an `ApplicationContext` using `SpringApplication` but does not provide *any* servlet environment (mock or otherwise).

### 3. Mocking and spying beans

**It’s sometimes necessary to mock certain components within your application context when running tests.** For example, you may have a facade over some remote service that’s unavailable during development. Mocking can also be useful when you want to simulate failures that might be hard to trigger in a real environment.

Spring Boot includes a `@MockBean` annotation that can be used to define a Mockito mock for a bean inside your `ApplicationContext`. You can use the annotation to add new beans, or replace a single existing bean definition. The annotation can be used directly on test classes, on fields within your test, or on `@Configuration` classes and fields. When used on a field, the instance of the created mock will also be injected. Mock beans are automatically reset after each test method.

```
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTests {

    @MockBean
    private RemoteService remoteService;

    @Autowired
    private Reverser reverser;

    @Test
    public void exampleTest() {
        // RemoteService has been injected into the reverser bean
        given(this.remoteService.someCall()).willReturn("mock");
        String reverse = reverser.reverseSomeCall();
        assertThat(reverse).isEqualTo("kcom");
    }

}
```

### 4. Auto-configured JSON tests

To test that Object JSON serialization and deserialization is working as expected you can use the `@JsonTest` annotation. `@JsonTest` will auto-configure Jackson `ObjectMapper`, any `@JsonComponent` beans and any Jackson `Modules`. It also configures `Gson` if you happen to be using that instead of, or as well as, Jackson. If you need to configure elements of the auto-configuration you can use the `@AutoConfigureJsonTesters` annotation.

Spring Boot includes AssertJ based helpers that work with the JSONassert and JsonPath libraries to check that JSON is as expected. The `JacksonTester`, `GsonTester` and `BasicJsonTester` classes can be used for Jackson, Gson and Strings respectively. Any helper fields on the test class can be `@Autowired` when using `@JsonTest`.

```
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.json.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.json.*;
import org.springframework.test.context.junit4.*;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@JsonTest
public class MyJsonTests {

    @Autowired
    private JacksonTester<VehicleDetails> json;

    @Test
    public void testSerialize() throws Exception {
        VehicleDetails details = new VehicleDetails("Honda", "Civic");
        // Assert against a `.json` file in the same package as the test
        assertThat(this.json.write(details)).isEqualToJson("expected.json");
        // Or use JSON path based assertions
        assertThat(this.json.write(details)).hasJsonPathStringValue("@.make");
        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.make")
                .isEqualTo("Honda");
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"make\":\"Ford\",\"model\":\"Focus\"}";
        assertThat(this.json.parse(content))
                .isEqualTo(new VehicleDetails("Ford", "Focus"));
        assertThat(this.json.parseObject(content).getMake()).isEqualTo("Ford");
    }

}
```

### 5. Auto-configured Spring MVC tests

To test Spring MVC controllers are working as expected you can use the `@WebMvcTest` annotation. `@WebMvcTest` will auto-configure the Spring MVC infrastructure and limit scanned beans to `@Controller`, `@ControllerAdvice`, `@JsonComponent`, `Filter`, `WebMvcConfigurer` and `HandlerMethodArgumentResolver`. Regular `@Component` beans will not be scanned when using this annotation.

Often `@WebMvcTest` will be limited to a single controller and used in combination with `@MockBean` to provide mock implementations for required collaborators.

`@WebMvcTest` also auto-configures `MockMvc`. Mock MVC offers a powerful way to quickly test MVC controllers without needing to start a full HTTP server.

| ![[Tip]](tip.png)                                            |
| ------------------------------------------------------------ |
| You can also auto-configure `MockMvc` in a non-`@WebMvcTest` (e.g. `SpringBootTest`) by annotating it with `@AutoConfigureMockMvc`. |

```
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserVehicleController.class)
public class MyControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserVehicleService userVehicleService;

    @Test
    public void testExample() throws Exception {
        given(this.userVehicleService.getVehicleDetails("sboot"))
                .willReturn(new VehicleDetails("Honda", "Civic"));
        this.mvc.perform(get("/sboot/vehicle").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andExpect(content().string("Honda Civic"));
    }

}
```

```
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserVehicleController.class)
public class MyControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserVehicleService userVehicleService;

    @Test
    public void testExample() throws Exception {
        given(this.userVehicleService.getVehicleDetails("sboot"))
                .willReturn(new VehicleDetails("Honda", "Civic"));
        this.mvc.perform(get("/sboot/vehicle").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andExpect(content().string("Honda Civic"));
    }

}
```

### 6. Auto-configured Data JPA tests

`@DataJpaTest` can be used if you want to test JPA applications. By default it will configure an in-memory embedded database, scan for `@Entity` classes and configure Spring Data JPA repositories. Regular `@Component` beans will not be loaded into the `ApplicationContext`.

Data JPA tests are transactional and rollback at the end of each test by default, see the [relevant section](https://docs.spring.io/spring/docs/4.3.11.RELEASE/spring-framework-reference/htmlsingle#testcontext-tx-enabling-transactions) in the Spring Reference Documentation for more details. If that’s not what you want, you can disable transaction management for a test or for the whole class as follows:

```
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ExampleNonTransactionalTests {

}
```

Data JPA tests may also inject a [`TestEntityManager`](https://github.com/spring-projects/spring-boot/tree/v1.5.7.RELEASE/spring-boot-test-autoconfigure/src/main/java/org/springframework/boot/test/autoconfigure/orm/jpa/TestEntityManager.java) bean which provides an alternative to the standard JPA `EntityManager` specifically designed for tests. If you want to use `TestEntityManager` outside of `@DataJpaTests` you can also use the `@AutoConfigureTestEntityManager` annotation. A `JdbcTemplate` is also available if you need that.

```
import org.junit.*;
import org.junit.runner.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExampleRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    public void testExample() throws Exception {
        this.entityManager.persist(new User("sboot", "1234"));
        User user = this.repository.findByUsername("sboot");
        assertThat(user.getUsername()).isEqualTo("sboot");
        assertThat(user.getVin()).isEqualTo("1234");
    }

}
```

### 7. Auto-configured REST clients

The `@RestClientTest` annotation can be used if you want to test REST clients. By default it will auto-configure Jackson and GSON support, configure a `RestTemplateBuilder` and add support for `MockRestServiceServer`. The specific beans that you want to test should be specified using `value` or `components` attribute of `@RestClientTest`:



```
@RunWith(SpringRunner.class)
@RestClientTest(RemoteVehicleDetailsService.class)
public class ExampleRestClientTest {

    @Autowired
    private RemoteVehicleDetailsService service;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void getVehicleDetailsWhenResultIsSuccessShouldReturnDetails()
            throws Exception {
        this.server.expect(requestTo("/greet/details"))
                .andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));
        String greeting = this.service.callRestService();
        assertThat(greeting).isEqualTo("hello");
    }

}
```

## Appendix D. Test auto-configuration annotations

Here is a table of the various `@…Test` annotations that can be used to test slices of your application and the auto-configuration that they import by default:

| Test slice        | Imported auto-configuration                                  |
| ----------------- | ------------------------------------------------------------ |
| `@DataJpaTest`    | `org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration` `org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration` `org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration` `org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration` `org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration` `org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration` `org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration` `org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration` `org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration` `org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration` `org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManagerAutoConfiguration` |
|                   |                                                              |
|                   |                                                              |
| `@JsonTest`       | `org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration` `org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration` `org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration` `org.springframework.boot.test.autoconfigure.json.JsonTestersAutoConfiguration` |
| `@RestClientTest` | `org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration` `org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration` `org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration` `org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration` `org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration` `org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerAutoConfiguration` `org.springframework.boot.test.autoconfigure.web.client.WebClientRestTemplateAutoConfiguration` |
| `@WebMvcTest`     | `org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration` `org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration` `org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration` `org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration` `org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration` `org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration` `org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration` `org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration` `org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration` `org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration` `org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration` `org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration` `org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration` `org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration` `org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration` `org.springframework.boot.test.autoconfigure.web.servlet.MockMvcSecurityAutoConfiguration` `org.springframework.boot.test.autoconfigure.web.servlet.MockMvcWebClientAutoConfiguration` `org.springframework.boot.test.autoconfigure.web.servlet.MockMvcWebDriverAutoConfiguration` |

## 41.4 Test utilities

### 41.4.1 ConfigFileApplicationContextInitializer

`ConfigFileApplicationContextInitializer` is an `ApplicationContextInitializer` that can apply to your tests to load Spring Boot `application.properties` files. You can use this when you don’t need the full features provided by `@SpringBootTest`.

```
@ContextConfiguration(classes = Config.class,
    initializers = ConfigFileApplicationContextInitializer.class)
```

| ![[Note]](https://docs.spring.io/spring-boot/docs/1.5.7.RELEASE/reference/html/images/note.png) |
| ------------------------------------------------------------ |
| Using `ConfigFileApplicationContextInitializer` alone won’t provide support for `@Value("${…}")` injection. Its only job is to ensure that `application.properties` files are loaded into Spring’s `Environment`. For `@Value` support you need to either additionally configure a `PropertySourcesPlaceholderConfigurer` or use `@SpringBootTest` where one will be auto-configured for you. |

### 41.4.2 EnvironmentTestUtils

`EnvironmentTestUtils` allows you to quickly add properties to a `ConfigurableEnvironment` or `ConfigurableApplicationContext`. Simply call it with `key=value` strings:

```
EnvironmentTestUtils.addEnvironment(env, "org=Spring", "name=Boot");
```

### 41.4.3 OutputCapture

`OutputCapture` is a JUnit `Rule` that you can use to capture `System.out` and `System.err` output. Simply declare the capture as a `@Rule` then use `toString()` for assertions:

```
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class MyTest {

    @Rule
    public OutputCapture capture = new OutputCapture();

    @Test
    public void testName() throws Exception {
        System.out.println("Hello World!");
        assertThat(capture.toString(), containsString("World"));
    }

}
```

## Mockito

#### 1. Mock & Spy

##### Mock

- optionally specify how it should behave via [`Answer`](http://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/stubbing/Answer.html)/[`MockSettings`](http://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/MockSettings.html)
- [`when()`](http://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#when-T-)/[`given()`](http://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/BDDMockito.html#given-T-) to specify how a mock should behave
- If the provided answers don’t fit your needs, write one yourself extending the [`Answer`](http://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/stubbing/Answer.html) interface

##### Spy

partial mocking, real methods are invoked but still can be verified and stubbed

#### 2. when & given

hese are just different styles. The first is the normal Mockito syntax and the second just tries to fit nicer into BDD style tests 

#### 3. @InjectMock

automatically inject mocks/spies fields annotated with `@Spy` or `@Mock`

#### 4. @capture

to check methods were called with given arguments

- can use flexible argument matching, for example any expression via the [`any()`](http://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/ArgumentMatchers.html#any--)
- or capture what arguments were called using [`@Captor`](http://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Captor.html) instead



## JUNIT5 vs JUNIT4

