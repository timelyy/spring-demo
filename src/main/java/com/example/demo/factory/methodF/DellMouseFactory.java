package com.example.demo.factory.methodF;

import com.example.demo.factory.entity.Mouse;
import com.example.demo.factory.entity.impl.DellMouse;

public class DellMouseFactory implements MouseFactory {
    @Override
    public Mouse getMouse() {
        return new DellMouse();
    }
}
