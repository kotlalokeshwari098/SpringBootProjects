package com.javaspring.telusko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TeluskoApplication {

    public static void main(String[] args) {

        ApplicationContext context=SpringApplication.run(TeluskoApplication.class, args);
//springApplication.run() this one creates a container ioc in the jvm where objects gets created
        //ofc that is also itself is an object of type ApplicationContext
        //so we are using context. to get the methods to call the dev class methods;

        Dev obj=context.getBean(Dev.class);
        obj.display();

    }

}
