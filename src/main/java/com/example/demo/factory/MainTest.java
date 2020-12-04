package com.example.demo.factory;


import com.example.demo.factory.entity.impl.DellKeyboard;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        DellKeyboard dellKeyboard = new DellKeyboard();
        Class aClass = dellKeyboard.getClass();
        Class dellKeyboardClass = DellKeyboard.class;
        Class aClass1 = Class.forName("com.example.demo.factory.entity.impl.DellKeyboard");
        System.out.println(aClass.equals(dellKeyboardClass));
        System.out.println(dellKeyboardClass.equals(aClass1));
        Constructor constructor = dellKeyboardClass.getConstructor();
        DellKeyboard dellKeyboard1 = (DellKeyboard) constructor.newInstance();
        Field gongyou = dellKeyboardClass.getField("gongyou");
        gongyou.setAccessible(true);
        gongyou.set(dellKeyboard1,"1111");
        Method sayHi = dellKeyboardClass.getMethod("sayHi");
        sayHi.setAccessible(true);
        sayHi.invoke(dellKeyboard1);
        System.out.println(dellKeyboard1);

    }
}
