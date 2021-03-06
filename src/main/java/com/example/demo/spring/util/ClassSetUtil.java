package com.example.demo.spring.util;

import com.example.demo.simplet.Simple1;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ClassSetUtil {

    public static final String FILEClASS = ".class";

    public static Set<Class<?>> getClassByPackage(String packageName) throws IOException {
        Set<Class<?>> classSet = new HashSet<>();
        ClassLoader classLoader = getClassLoader();
        String name = packageName.replace(".","/");
        URL url = classLoader.getResource(name);
        if(url == null){
            log.info("url is null");
            throw  new RuntimeException("url is not null");
        }
        if(url.getProtocol().endsWith("file")){
            String path = url.getPath();
            File file = new File(path);
            getClassInstence(classSet,file,packageName);
        }
        return classSet;
    }

    private static void getClassInstence(Set<Class<?>> classSet, File file, String packageName) {
        if(!file.isDirectory()){
            log.info("file is not directory");
            return;
        }
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname.isDirectory()){
                    return true;
                }
                String absolutePath = pathname.getAbsolutePath();
                if(absolutePath.endsWith(FILEClASS)){
                    String replace = absolutePath.replace(String.valueOf(File.separatorChar), ".");
                    String substring = replace.substring(replace.indexOf(packageName));
                    String replace1 = substring.replace(FILEClASS, "");
                    Class<?> aClass = null;
                    try {
                        aClass = Class.forName(replace1);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        throw new RuntimeException("class not found");
                    }
                    classSet.add(aClass);
                }
                return false;
            }
        });

        if (files != null) {
            for (File fileDirectroy:files
                 ) {
                getClassInstence(classSet,fileDirectroy,packageName);
            }
        }
    }


    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
//        Set<Class<?>> classByPackage = getClassByPackage("com.example.demo.entity");
//        System.out.println(classByPackage);
        Simple1 instance = Simple1.getInstance();
        Field declaredField = Simple1.class.getDeclaredField("instence");
        declaredField.setAccessible(true);
        Constructor<Simple1> declaredConstructor = Simple1.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Simple1 simple1 = declaredConstructor.newInstance();
        declaredField.set(instance,simple1);

    }

    public static <T> T getClassInstence(Class<T> clazz,Boolean isDeclear) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(isDeclear);
        return declaredConstructor.newInstance();
    }
}
