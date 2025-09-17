package com.javaspring.webapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class HomeController {

    @RequestMapping("/")
//    @ResponseBody  or use the @RestController
    public static String greet(){
        return "Welcome";
    }

    @RequestMapping("/about")
    public String about(){
        return "WE TEACH MILLIONS OF STUDENTS";
    }
}
