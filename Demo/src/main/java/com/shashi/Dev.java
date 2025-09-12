package com.shashi;

public class Dev {
    private Laptop laptop;

    private int age;

    public Dev(){
        System.out.println("dev constructor");
    }

    //constructor injection use this xml tag:
    // <constructor-arg name="age" index="0" value="23"/>
    public Dev(int age){
       this.age=age;
    }

    //setter injection
    public void setAge(int age){
        this.age=age;
    }

    public int getAge(){
        return age;
    }
    //for this use <property name="age" value="12"/>


    //setter and getter injection for laptop
    public void setLaptop(Laptop laptop){
        this.laptop=laptop;
    }

    public Laptop getLaptop(){
        return laptop;
    }

    //for setter and getter injection use this tags in xml
    //<property name="laptop" ref="laptop"/>

    //constuctor injection for laptop
    //<constructor-arg name="laptop" ref="laptop"/>
    //here ref should be ==
    // <bean class="com.shashi.Laptop" id="laptop">
    //        </bean> its own laptop bean ka id
    public Dev(Laptop laptop){
        this.laptop=laptop;
    }

    public boolean build(){
        System.out.println("Inside dev project");
        laptop.compile();
        return false;
    }
}
