package com.example.demo.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
    public HelloWorldBean helloWorld() {
        return new HelloWorldBean("Hello world");
    }

    @GetMapping(path = "/hello-world/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello Mr %s", name.toUpperCase(Locale.ROOT)));
    }
}
