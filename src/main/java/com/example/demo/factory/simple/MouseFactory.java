package com.example.demo.factory.simple;

import com.example.demo.factory.entity.Mouse;
import com.example.demo.factory.entity.impl.DellMouse;
import com.example.demo.factory.entity.impl.HuiPuMouse;

public class MouseFactory {

    public Mouse getMouse(int i){
        switch (i){
            case 1:
                return new DellMouse();
            case 2:
                return new HuiPuMouse();
            default:
                return new DellMouse();
        }
    }
}
