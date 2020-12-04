package com.example.demo.factory.entity.impl;

import com.example.demo.factory.entity.Mouse;

public class HuiPuMouse implements Mouse {

    @Override
    public void sayHi() {
        System.out.println("我是惠普鼠标");
    }
}
