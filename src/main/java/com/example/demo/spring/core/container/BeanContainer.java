package com.example.demo.spring.core.container;

import com.example.demo.spring.core.annotation.*;
import com.example.demo.spring.util.ClassSetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
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
        return iocContainer.keySet();
    }

    public Set<Object> getObjects(){
        return new HashSet<>(iocContainer.values());
    }

    public Set<Class<?>> getClassByAnnotation(Class<? extends Annotation> clazz){
        Set<Class<?>> set = new HashSet<>();
        Set<Class<?>> classes = getClasses();
        if(classes != null && classes.size() > 0){
            for (Class<?> clazzz : classes){
                if(clazzz.isAnnotationPresent(clazz)){
                    set.add(clazzz);
                }
            }
        }
        return set;
    }

    /**
     *
     * @param clazz
     * @return
     */
    public Set<Class<?>> getClassBySupperClass(Class<?> clazz){
        Set<Class<?>> set = new HashSet<>();
        Set<Class<?>> classes = getClasses();
        if(null == classes || classes.size() == 0){
            log.warn("classes is null");
            throw new RuntimeException("classes is null");
        }
        for (Class<?> aClass : classes) {
            if(clazz.isAssignableFrom(aClass) && !classes.equals(aClass)){
                set.add(aClass);
            }
        }
        return set;
    }


    public int size(){
        return iocContainer.size();
    }

    public void dependencyInjection() throws IllegalAccessException {
        Set<Class<?>> classes = getClasses();
        if(classes == null || classes.size() ==0 ){
            throw new RuntimeException("class is not found");
        }
        for (Class<?> aClass : classes) {
            Field[] declaredFields = aClass.getDeclaredFields();
            if(declaredFields.length > 0){
                for (Field field : declaredFields){
                    if(field.isAnnotationPresent(Autowaire.class)){
                        Class<?> type = field.getType();
                        Object obj = iocContainer.get(type);
                        Autowaire annotatedType = field.getAnnotation(Autowaire.class);
                        String value = annotatedType.value();
                        if(obj != null){
                            field.setAccessible(true);
                            Object oParent = iocContainer.get(aClass);
                            field.set(oParent,obj);
                        }else{
                            //如果找不到，就是去找实现类里面包含注解value的
                            Set<Class<?>> classBySupperClass = getClassBySupperClass(type);
                            if(classBySupperClass == null || classBySupperClass.size() >0){
                                for (Class<?> bySupperClass : classBySupperClass) {
                                    String simpleName = bySupperClass.getSimpleName();
                                    if(simpleName.equals(value)){
                                        field.setAccessible(true);
                                        Object oParent = iocContainer.get(aClass);
                                        Object byObj = iocContainer.get(bySupperClass);
                                        field.set(oParent,byObj);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
