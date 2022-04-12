package docrob.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "myName"
            , required = false) String myName) {
        if(myName != null) {
            return "Hello " + myName;
        }
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/hello/{myName}")
    public String helloYou(@PathVariable String myName) {
        return "Hello " + myName;
    }
}