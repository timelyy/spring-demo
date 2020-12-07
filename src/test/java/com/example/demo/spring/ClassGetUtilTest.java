package com.example.demo.spring;

import com.example.demo.spring.util.ClassSetUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

public class ClassGetUtilTest {

    @Test
    public void tsetClassGet() throws IOException {
        Set<Class<?>> classByPackage = ClassSetUtil.getClassByPackage("com.example.demo.entity");
        Assertions.assertEquals(2,classByPackage.size());
    }
}


