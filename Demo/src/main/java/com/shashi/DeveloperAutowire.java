package com.shashi;

public class DeveloperAutowire {

    private Computer computer;

    public Computer getComputer(){
        return computer;
    }

    public void setComputer(Computer computer){
        this.computer=computer;
    }
    public void autowire(){
        System.out.println(computer);
        computer.compile();
        System.out.println("Inside Devolper autowire");
    }
}
