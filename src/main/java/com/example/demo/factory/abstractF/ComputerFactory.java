package com.example.demo.factory.abstractF;

import com.example.demo.factory.entity.Keyboard;
import com.example.demo.factory.entity.Mouse;

public interface ComputerFactory {

    Mouse getMouse();

    Keyboard getKeyMouse();
}
