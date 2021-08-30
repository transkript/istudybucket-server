package com.feljtech.istudybucket.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class HelloController {
    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model, @RequestParam(value="name", required = false, defaultValue = "Elroy Kanye") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
}
