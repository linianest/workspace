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
public class SingletonObject4 {
    private static SingletonObject4 instance;

    private SingletonObject4() {
        // empty
    }

    /**
     * double check
     *
     * @return
     */
    public static SingletonObject4 getInstance() {
        if (null == instance) {
            synchronized (SingletonObject4.class) {
                if (null == instance)
                    instance = new SingletonObject4();
            }
        }
        return SingletonObject4.instance;
    }
}
