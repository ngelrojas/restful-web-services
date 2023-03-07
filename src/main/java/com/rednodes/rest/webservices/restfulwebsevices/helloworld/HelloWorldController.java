package com.rednodes.rest.webservices.restfulwebsevices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rednodes.rest.webservices.restfulwebsevices.helloworld.HelloWorldBean;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/hello-world")
    public String HelloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }
}
