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
 * @Description TODO: 单例设计模式：懒汉式
 */
public class SingletonObject5 {
    // 使用 volatile关键字解决可能出现的空指针异常
    private static volatile SingletonObject5 instance;

    private SingletonObject5() {
        // empty
    }

    /**
     * double check
     *
     * @return
     */
    public static SingletonObject5 getInstance() {
        if (null == instance) {
            synchronized (SingletonObject5.class) {
                if (null == instance)
                    instance = new SingletonObject5();
            }
        }
        return SingletonObject5.instance;
    }
}
