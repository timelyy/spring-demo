package com.example.demo.simplet;

public class Simple1 {

    private static final Simple1 instence = new Simple1();

    private Simple1(){
    }

    public static Simple1 getInstance(){
        return instence;
    }

}
