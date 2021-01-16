package com.ln.concurrent.classloader.chapter5;

import com.ln.concurrent.classloader.chapter3.MyClassLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.classloader.chapter5
 * @Name:SimpleClassLoaderTest
 * @Author:linianest
 * @CreateTime:2021/1/6 16:09
 * @version:1.0
 * @Description TODO: 打破双亲先加载的机制
 */
public class SimpleClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException{
        final SimpleClassLoader simpleClassLoader = new SimpleClassLoader("E:\\workspace\\java-concurrency\\classLoader\\target\\classes");

        final Class<?> loadClass = simpleClassLoader.loadClass("com.ln.concurrent.classloader.chapter5.SimpleObject");
        System.out.println(simpleClassLoader);
        System.out.println(loadClass.getClassLoader());


    }
}
