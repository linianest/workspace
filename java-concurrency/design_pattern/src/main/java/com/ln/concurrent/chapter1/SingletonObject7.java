package com.ln.concurrent.chapter1;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter1
 * @version: 1.0
 */

import java.util.stream.IntStream;

/**
 * @ClassName:SingletonObject6
 * @Author:linianest
 * @CreateTime:2020/3/21 17:09
 * @version:1.0
 * @Description TODO: 单例模式(枚举)：优雅的方式
 */
public class SingletonObject7 {

    private SingletonObject7() {
    }

    /**
     * 枚举类型线程安全，构造函数，只会被装载一次
     */
    private enum Singleton {
        INSTANCE;

        private final SingletonObject7 instance;

        Singleton() {
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance() {
            return instance;
        }
    }


    public static SingletonObject7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).forEach(i -> new Thread(String.valueOf(i)) {
            @Override
            public void run() {
                System.out.println(SingletonObject7.getInstance());
            }
        }.start());
    }
}
