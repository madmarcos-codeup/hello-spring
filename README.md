# Hello Spring

NOTE: parts of this were shamelessly copied from the Spring Boot Guide (see references at bottom). Thanks Spring Boot!!

## Create the project in IntelliJ

In IntelliJ, select **New Project**

- select Spring Initializr 
  - project name: **hello-spring**
  - group: **\<your name>**
- click Next
  - put a check in Web -> **Spring Web**
- Click Finish

The class automatically created is the entry point for your Spring Boot project. There is not much to it, but there is a curious annotation:
- **@SpringBootApplication** is a convenience annotation that adds all of the following:

  - **@Configuration**: Tags the class as a source of bean definitions for the application context.

  - **@EnableAutoConfiguration**: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.

  - **@ComponentScan**: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.

With this annotation, Spring will automatically try to find and load any class you have annotated as a controller in the current directory.

--------------------------------

## Time to add an endpoint!

1. Make a new package called `controller`

2. create a Java class in `controller` package called `HelloController`

3. add the following code to your class:
```Java
@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		return "Greetings from Spring Boot!";
	}
}
```
and add appropriate imports.

4. Add a new annotation to HelloSpringApplication:
   `@ComponentScan(basePackages = {"<your name>.controller"})`

    Add it above the `@SpringBootApplication` annotation.

The class is flagged as a ``@RestController``, meaning it is ready for use by Spring MVC to handle web requests.
`@GetMapping` maps /hello to the hello() method, i.e., when Spring receives a GET request with the /hello path, it runs the code in the hello() method. 

When invoked from a browser or by using curl on the command line, the method returns pure text. That is because
``@RestController`` combines ``@Controller`` and ``@ResponseBody``,
two annotations that results in web requests returning data rather than a view.

----------------------------------

## Configuring Spring Boot applications

Most/all configuration is handled in ``src/main/resources/application.properties``
including sensitive info like credentials.

### DO NOT PUT IN YOUR GIT REPO!!

#### Add it to your .gitignore immediately!

Let's change the port used by your Spring Boot application. Add to your ``application.properties``:

```server.port=8081```


### Now press the jolly Green play button 
and direct your browser to http://localhost/hello 

--------------------------------

### Using query parameters

Recall that query parameters are key/value pairs that you pass after the path in the endpoint URL. Query parameters are used to modify the way an endpoint works or provide optional information, e.g., values for searching, or sort direction.

Query parameters look like this (the two items at the end of the URL):
`https://my.host.com/path/to/stuff?parameter1=value1&parameter2=value2`

Let's add an optional query parameter to the `hello` endpoint. 

```Java
@GetMapping("/hello")
    public String hello(@RequestParam(name = "myName"
            , required = false) String myName) {
```

- `name =`: specifies the parameter's identifier in the url, e.g., http://localhost/hello?myName=xxx
- `required =`: allows caller to not provide the parameter. check for null in the controller
- you can include a default value instead of required, in case parameter is not provided
  - `defaultValue =` : \<some default value>

#### Fun experiment:

If you want to see the default error screen for Spring, try to access an endpoint that does not exist, e.g., `http://localhost:8081/goodbye`

We call this a "white label" error message.

--------------------------------

### Using path parameters

Path parameters allow us to target specific resources, like individual records. Path parameters are provided as part of the URL's path.

Path parameters look like this:
`https://my.host.com/muppets/elmo`

The above example path provides information specific to the muppet, Elmo. Whereas the URL `https://my.host.com/muppets` would provide general information about muppets.

Let's add a new endpoint where we can pass a person's name as a path parameter to say hello.

```Java
@GetMapping("/hello/{myName}")
    public String helloYou(@PathVariable String myName) {
        return "Hello " + myName;
    }
```


## Resources:

[Spring Boot Guide](https://spring.io/guides/gs/spring-boot/)

[Baeldung article about request parameters](https://www.baeldung.com/spring-request-param)

[Baeldung article about path parameters](https://www.baeldung.com/spring-pathvariable)
