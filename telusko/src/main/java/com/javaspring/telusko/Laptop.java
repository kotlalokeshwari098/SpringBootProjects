package com.javaspring.telusko;


import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {
    public void compile(){
        System.out.println("Laptop Compiling with 404 errors");
    }
}
