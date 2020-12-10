package com.example.demo.simplet;

public class Simple2 {
    private static volatile Simple2 simple2;

    private Simple2(){
    }

    public static Simple2 getInstance(){
        if(simple2 == null){
            synchronized (Simple2.class){
                if(simple2 == null){
                    return new Simple2();
                }
            }
        }
        return simple2;
    }
}
