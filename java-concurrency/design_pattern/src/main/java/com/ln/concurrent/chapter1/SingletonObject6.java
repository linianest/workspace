package com.ln.concurrent.chapter1;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter1
 * @version: 1.0
 */

/**
 * @ClassName:SingletonObject6
 * @Author:linianest
 * @CreateTime:2020/3/21 17:09
 * @version:1.0
 * @Description TODO: 单例模式：优雅的方式
 */
public class SingletonObject6 {


    private SingletonObject6() {
    }

    private static class InstanceHolder {
        private final static SingletonObject6 instance = new SingletonObject6();
    }

    public static SingletonObject6 getInstance() {
        return InstanceHolder.instance;
    }
}
