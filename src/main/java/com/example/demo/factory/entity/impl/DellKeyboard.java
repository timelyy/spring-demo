package com.example.demo.factory.entity.impl;

import com.example.demo.factory.entity.Keyboard;

public class DellKeyboard implements Keyboard {

    private String gongyou;

    public String siyou;

    @Override
    public void sayHi() {
        System.out.println("我是dell键盘");
    }
}
