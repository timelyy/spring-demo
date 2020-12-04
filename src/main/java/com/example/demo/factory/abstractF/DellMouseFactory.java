package com.example.demo.factory.abstractF;

import com.example.demo.factory.entity.Keyboard;
import com.example.demo.factory.entity.Mouse;
import com.example.demo.factory.entity.impl.DellKeyboard;
import com.example.demo.factory.entity.impl.DellMouse;

public class DellMouseFactory implements ComputerFactory {
    @Override
    public Mouse getMouse() {
        return new DellMouse();
    }

    @Override
    public Keyboard getKeyMouse() {
        return new DellKeyboard();
    }
}
