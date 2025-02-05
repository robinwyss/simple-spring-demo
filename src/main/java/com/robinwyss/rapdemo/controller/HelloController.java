package com.robinwyss.rapdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    
    @GetMapping("/hello")
    public String hello() {
        return "H E L L O!";
    }

    @GetMapping("/hello/**")
    public String hello2() {
        return "H E L L O ** !";
    }

}
