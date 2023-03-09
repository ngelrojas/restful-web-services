package com.rednodes.rest.webservices.restfulwebsevices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rednodes.rest.webservices.restfulwebsevices.helloworld.HelloWorldBean;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/hello-world")
    public String HelloWorld() {
        return "Hello World";
    }
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean HelloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(
                String.format("Hello World, %s", name)
        );
    }
}
