package com.example.demo.factory.abstractF;

import com.example.demo.factory.entity.Keyboard;
import com.example.demo.factory.entity.Mouse;
import com.example.demo.factory.entity.impl.HuiPuKeyboard;
import com.example.demo.factory.entity.impl.HuiPuMouse;
import com.example.demo.factory.methodF.MouseFactory;

public class HuiPuMouseFactory implements ComputerFactory {
    @Override
    public Mouse getMouse() {
        return new HuiPuMouse();
    }

    @Override
    public Keyboard getKeyMouse() {
        return new HuiPuKeyboard();
    }


}
