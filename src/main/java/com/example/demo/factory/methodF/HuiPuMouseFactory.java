package com.example.demo.factory.methodF;

import com.example.demo.factory.entity.Mouse;
import com.example.demo.factory.entity.impl.HuiPuMouse;

public class HuiPuMouseFactory implements MouseFactory {
    @Override
    public Mouse getMouse() {
        return new HuiPuMouse();
    }
}
