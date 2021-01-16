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
public class SingletonObject2 {
    private static SingletonObject2 instance;

    private SingletonObject2() {
        // empty
    }
    // TODO 实现了懒加载，但是可能创建多个实例
    public static SingletonObject2 getInstance() {
        if (null == instance)
            instance = new SingletonObject2();
        return SingletonObject2.instance;
    }
}
