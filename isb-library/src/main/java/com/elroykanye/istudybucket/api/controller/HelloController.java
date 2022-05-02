package com.elroykanye.istudybucket.api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("SameReturnValue")
@RequestMapping(value = "hello")
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

    @GetMapping(value = "verify")
    public ModelAndView verifyEmail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("verify-email");
        modelAndView.addObject("recipientName", "Elroy Kanye");
        modelAndView.addObject("activationUrl", "https://github.com/elroykanye");
        return modelAndView;
    }
}
