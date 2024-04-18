package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/actuator/hello_world")
    public String getData(){
        return "hello, world!";
    }
}
