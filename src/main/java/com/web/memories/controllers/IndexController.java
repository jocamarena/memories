package com.web.memories.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/test")
    public ModelAndView getIndex(ModelAndView modelAndView){
        modelAndView.addObject("username", "jcamarena");
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
}
