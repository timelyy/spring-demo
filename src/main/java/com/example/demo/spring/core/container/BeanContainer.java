package com.example.demo.spring.core.container;

import com.example.demo.spring.core.annotation.Conponent;
import com.example.demo.spring.core.annotation.Controller;
import com.example.demo.spring.core.annotation.Mapper;
import com.example.demo.spring.core.annotation.Service;
import com.example.demo.spring.util.ClassSetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class BeanContainer {

    private final Map<Class<?>,Object> iocContainer = new ConcurrentHashMap<>();

    private boolean isload = false;

    private static final List<Class<? extends Annotation>> annoList = Arrays.asList(Controller.class, Service.class
            , Mapper.class, Conponent.class);

    private BeanContainer(){}

    private enum BeanContainerEnum{
        INSTENCE;
        private BeanContainer beanContainer;
        BeanContainerEnum(){
            beanContainer = new BeanContainer();
        }
    }

    public static BeanContainer getInstance(){
        return BeanContainerEnum.INSTENCE.beanContainer;
    }

    public synchronized Set<Class<?>> setIocContainerInit(String pakageName) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Set<Class<?>> classByPackage = ClassSetUtil.getClassByPackage(pakageName);
        if(classByPackage != null && classByPackage.size() > 0){
            for(Class<?> clazz : classByPackage){
                for (Class<? extends Annotation> annoClass : annoList){
                    if(clazz.isAnnotationPresent(annoClass)){
                        Object classInstence = ClassSetUtil.getClassInstence(clazz, true);
                        iocContainer.put(clazz,classInstence);
                    }
                }
            }
            isload = true;
        }else{
            log.warn("Annotation Class not founc");
        }
        return classByPackage;
    }

    public boolean isload(){
        return isload;
    }


    public Object add(Class<?> clazz,Object object) {
        return iocContainer.put(clazz, object);
    }

    public Object remove(Class<?> clazz){
        return iocContainer.remove(clazz);
    }

    public Object getByClass(Class<?> clazz){
        return iocContainer.get(clazz);
    }

    public Set<Class<?>> getClasses(){

    }


}
