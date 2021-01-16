package com.ln.concurrent.chapter10;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter10
 * @Name:ThreadLocalSimpleTest
 * @Author:linianest
 * @CreateTime:2020/3/28 21:00
 * @version:1.0
 * @Description TODO: ThreadLocal线程
 */
public class ThreadLocalSimpleTest {

    /**
     * threadlocal 通过initialValue设置初始值
     */
    private static ThreadLocal<String> threadLocal = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return "Alex";
        }
    };

    public static void main(String[] args) throws InterruptedException {

//        threadLocal.set("Alex");
        Thread.sleep(1000);
        System.out.println(threadLocal.get());
    }
}
