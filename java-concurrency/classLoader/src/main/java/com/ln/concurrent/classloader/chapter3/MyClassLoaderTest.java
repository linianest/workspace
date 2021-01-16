package com.ln.concurrent.classloader.chapter3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.classloader.chapter3
 * @Name:MyClassLoaderTest
 * @Author:linianest
 * @CreateTime:2021/1/6 15:43
 * @version:1.0
 * @Description TODO: 测试
 */
public class MyClassLoaderTest {

    public String hello(){
        return "hello word.";
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        final MyClassLoader myclassload = new MyClassLoader("myclassload");
        final Class<?> loadClass = myclassload.loadClass("com.ln.concurrent.classloader.chapter3.MyClassLoaderTest");
        System.out.println(loadClass);
        System.out.println(loadClass.getClassLoader());

        final Object obj = loadClass.newInstance();
        // 指定方法名称以及返回值类型
        final Method method = loadClass.getMethod("hello", new Class<?>[]{});

        final Object result = method.invoke(obj, new Object[]{});
        System.out.println(result);


    }
}
