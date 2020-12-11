package com.example.demo.spring;

import com.example.demo.spring.core.container.BeanContainer;
import com.example.demo.spring.util.ClassSetUtil;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClassGetUtilTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    public static void init(){
        beanContainer = BeanContainer.getInstance();
    }

    @DisplayName("测试装载ioc")
    @Test
    @Order(1)
    public void setIoc() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        boolean isload = beanContainer.isload();
        Assertions.assertEquals(false,isload);
        Set<Class<?>> classes = beanContainer.setIocContainerInit("com.example");
        boolean isloadnew = beanContainer.isload();
        Assertions.assertEquals(true,isloadnew);
        Assertions.assertEquals(2,classes.size());
    }





}


