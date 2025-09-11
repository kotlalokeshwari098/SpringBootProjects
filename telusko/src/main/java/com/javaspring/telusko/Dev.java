package com.javaspring.telusko;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//tells spring 'YOU NEED TO CREATE OBJECT FOR THIS CLASS'
public class Dev {

    //@Autowired //field injection
//    private Laptop laptop;
    //it is connecting the dev with laptop class

//    ..loosecoupling
    @Autowired
    private Computer comp;

    //constructor injection
//    public Dev(Laptop laptop){
//        this.laptop=laptop;
//    }


    //setter injection
//    @Autowired //here need to mention autowired
//    public void setLaptop(Laptop laptop){
//        this.laptop=laptop;
//    }

    public void display(){
//        laptop.compile();
        comp.compile();
        System.out.println("Hello developers welcome to my channel!!!");
    }

}
