## Spring Boot annotations cheatsheet


### Spring ApplicationContext

<a id="spring-applicationcontext"></a>

<p>
    Spring ApplicationContext is where Spring holds instances of objects that it 
    has identified to be managed and distributed automatically. These are called beans.
</p>
<p>
    Bean management and the opportunity for dependency injection are some of Spring's main features.<br>
    Spring collects bean instances from our application and uses them at the appropriate time. 
    We can show bean dependencies to Spring without needing to handle the setup 
    and instantiation of those objects.
</p>
<p>
    The ability to use annotations like <a href="autowired"><i>@Autowired</i></a> to inject Spring-managed beans 
    into our application is a driving force for creating powerful and scalable code in Spring.
</p>

---

### Spring Boot Annotations:

<details id="springBootApplication">
<summary> <b>@SpringBootApplication</b> </summary>
<p>
    This annotation is used on the application class while setting up a Spring 
    Boot project. The class that is annotated with the <i>@SpringBootApplication</i> must be 
    kept in the base package. The one thing that the <i>@SpringBootApplication</i> does is a 
    component scan. But it will scan only its sub-packages. As an example, if you put 
    the class annotated with <i>@SpringBootApplication</i> in <code>com.example</code> 
    then <i>@SpringBootApplication</i> will scan all its sub-packages, such as 
    <code>com.example.a</code>, <code>com.example.b</code>, and <code>com.example.a.x</code>.
</p>
<p>
    The <i>@SpringBootApplication</i> is a convenient annotation that adds all the following:
</p>
<ul>
    <li><a href="#configuration"><i>@Configuration</i></a></li>
    <li><a href="#enableAutoConfiguration"><i>@EnableAutoConfiguration</i></a></li>
    <li><a href="#componentScan"><i>@ComponentScan</i></a></li>
</ul>
</details>

---

<details id="enableAutoConfiguration">
<summary> <b>@EnableAutoConfiguration</b> </summary>
<p>
    The <i>@EnableAutoConfiguration</i> annotation enables Spring Boot to auto-configure
    application context. Therefore, it automatically creates and registers beans 
    based on both the included jar files in the classpath and the beans defined by us.
</p>
<p>
    The package of the class declaring the @EnableAutoConfiguration annotation is 
    considered as the default. Therefore, we should always apply the 
    <i>@EnableAutoConfiguration</i> annotation in the root package so that every 
    sub-packages and class can be examined:
</p>

```
@Configuration
@EnableAutoConfiguration
public class EmployeeApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EmployeeApplication.class, args);
        // ...
    }
}
```

</details>

---

### Spring Boot Core Annotations:

<details id="bean">
<summary> <b>@Bean</b> </summary>
<p>
    This annotation is used at the method level. <b>@Bean</b> annotation works with 
    <a href="#configuration"><i>@Configuration</i></a> to create Spring beans. <br>
    Classes annotated with <a href="#configuration"><i>@Configuration</i></a> will 
    have methods to instantiate and configure dependencies. Such methods will be 
    annotated with <b>@Bean</b>. The method annotated with this annotation works 
    as bean ID and <u>it creates and returns the actual bean.</u>
</p>

```
@Configuration
public class AppConfig{
  @Bean
  public Person person(){
    return new Person(address());
  }
  @Bean
  public Address address(){
    return new Address();
  }
}
```
</details>

---

<details id="configuration">
<summary> <b>@Configuration</b> </summary>
<p>
    The <b>@Configuration</b> annotation indicates that the class has 
    <i><a href="#bean">@Bean</a></i> definition methods. So Spring container can
    process the class and instantiate nad configure <i>Spring Beans</i> (dependencies)
    to be used in the application.
</p>

```
@Configuration
public class DataConfig{ 
  @Bean
  public DataSource source(){
    DataSource source = new OracleDataSource();
    source.setURL();
    source.setUser();
    return source;
  }
  @Bean
  public PlatformTransactionManager manager(){
    PlatformTransactionManager manager = new BasicDataSourceTransactionManager();
    manager.setDataSource(source());
    return manager;
  }
}
```

</details>

---

<details id="componentScan">
<summary> <b>@ComponentScan</b> </summary>
Basically <a href="https://stackoverflow.com/a/62514430/19238684"><u>this answer</u>.</a>
<p>
    <b>@ComponentScan</b> annotation is used with <i><a href="#configuration">@Configuration</a></i>
    annotation to specify the package for Spring to scan for components.<br>
    <i><a href="#springBootApplication"> @SpringBootApplication</a> annotation implies 
    the <a href="#configuration">@Configuration</a>, <a href="#componentScan">@ComponentScan</a>, 
    and <a href="#enableAutoConfiguration">@EnableAutoConfiguration</a> annotations.</i>
</p>
<p>
    This annotation Scans the current package and all of its sub-packages for all
    <i><a href="#component">@Component</a></i>, 
    <i><a href="#controller">@Controller</a></i>,
    <i><a href="#service">@Service</a></i>,
    <i><a href="#repository">@Repository</a></i>,<br>
    and registers them to the <a href="#spring-applicationcontext">application context</a>.
    <br>
    You can also explicitly specify in what package the component scan should 
    start with the <code>basePackage</code> parameter.
</p>

```
package com.example.demo

@ComponentScan(basePackage = { "com.example" })
public class JavaConfiguration {
    @Bean
    public MyClass myClass() {
        return new myClass();
    }
}

```

</details>

---

<details id="autowired">
<summary> <b>@Autowired</b> </summary>
<p>
    The Spring framework enables automatic dependency injection. In othe
</p>
</details>

---

### Spring Boot Stereotype Annotations:

<details id="component"> 
<summary> <b>@Component</b> </summary>
<p>
    This annotation is used on classes to indicate a Spring component. <br> The 
    <i>@Component</i> annotation marks java class as a bean or say component so 
    that the <u>component-scanning</u> mechanism can add
    into the <a href="#spring-applicationcontext">application context</a>.
</p>
<p>In other words, without having to write any explicit code, Spring will:</p>
<ul>
    <li>Scan our application for classes annotated with <i>@Component</i></li>
    <li>Instantiate them and inject any specified dependencies into them</li>
    <li>Inject them wherever needed</li>
</ul>
</details>

---

<details id="controller">
<summary> <b>@Controller</b> </summary>
<p>
    This indicates that the annotated class is a Spring-managed controller that 
    provides methods annotated with <a href="#requestMapping"><i>@RequestMapping</i></a> to answer web requests.<br>
    Spring 4.0 introduced the <a href="#restController"><i>@RestController</i></a> annotation which combines both 
    <b>@Controller</b> and <a href="#responseBody"><i>@ResponseBody</i></a> and makes it easy to create RESTful services that return JSON objects.
</p>
</details>


---

<details id="service">
<summary> <b>@Service</b> </summary>
<p>
    We can use the <b>@Service</b> stereotype for classes that contain business logic 
    or classes which come in the service layer.
</p>
</details>

---

<details id="repository">
<summary> <b>@Repository</b> </summary>
<p>
    We can use the <b>@Repository</b> stereotype for DAO classes which are responsible 
    for providing access to database entities.
    If we are using Spring Data for managing database operations, then we should use 
    the Spring Data Repository interface instead of building our own 
    <b>@Repository</b>-annotated classes.
</p>
</details>

---
