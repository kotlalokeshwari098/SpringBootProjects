package com.shashi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        //to create container
        //after creating container it goes to spring.xml file
        //depending on the beans config the spring creates the
        //objects in that container of those classes
        ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
//        Dev obj=context.getBean(Dev.class);
//        Dev obj=(Dev) context.getBean("dev");
        //this is getbean by name so we are typecasting
//        obj.setAge(20);
//        System.out.println(obj.getLaptop());
//        System.out.println(obj.build());

        DeveloperAutowire obj=context.getBean(DeveloperAutowire.class);
        //get bean by type
        obj.autowire();
        System.out.println(obj.getComputer());
    }
}
