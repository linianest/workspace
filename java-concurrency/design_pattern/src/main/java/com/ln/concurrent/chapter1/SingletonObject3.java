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
 * @Description TODO: 单例设计模式：懒汉式(synchronized)
 */
public class SingletonObject3 {
    private static SingletonObject3 instance;

    private SingletonObject3() {
        // empty
    }
    /**
     * 但是性能慢，串行模式
     * @return
     */
    public synchronized static SingletonObject3 getInstance() {
        if (null == instance)
            instance = new SingletonObject3();
        return SingletonObject3.instance;
    }
}
