package com.robinwyss.rapdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "H E L L O!");
        return "hello";
    }

    @GetMapping("/hello/**")
    public String hello2(Model model) {
        model.addAttribute("message", "H E L L O ** !");
        return "hello";
    }

}
