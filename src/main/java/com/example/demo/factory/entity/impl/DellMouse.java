package com.example.demo.factory.entity.impl;

import com.example.demo.factory.entity.Mouse;

public class DellMouse implements Mouse {
    @Override
    public void sayHi() {
        System.out.println("我是Dell鼠标");
    }
}
