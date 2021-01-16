package com.ln.concurrent.chapter1;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter1
 * @version: 1.0
 */

/**
 * @ClassName:SingletonObject1
 * @Author:linianest
 * @CreateTime:2020/3/21 10:40
 * @version:1.0
 * @Description TODO: 单例设计模式：饿汉式
 */
public class SingletonObject1 {
    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1() {
        // empty
    }

    public static SingletonObject1 getInstance() {
        return instance;
    }
}
