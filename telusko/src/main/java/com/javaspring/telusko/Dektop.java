package com.javaspring.telusko;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Dektop implements Computer {
    public void compile(){
        System.out.println("Desktop Compiling with 404 errors");
    }
}
