package com.ln.concurrency.chapter3;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter3
 * @version: 1.0
 */

/**
 * @ClassName:CreateThread
 * @Author:linianest
 * @CreateTime:2020/3/14 9:42
 * @version:1.0
 * @Description TODO: 创建线程
 */
public class CreateThread {
    public static void main(String[] args) {
        // 1.不指定线程名称
        Thread t1 = new Thread();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("============");
            }
        };
        // 不指定线程执行的方法，线程不会执行任何的东西
        t1.start();
        t2.start();
        System.out.println(t1.getName());
        System.out.println(t2.getName());


        Thread t3 = new Thread("MyThreadName");
        // 指定线程需要执行的内容
        Thread t4 = new Thread(() -> {
            System.out.println("=============");
        });
        System.out.println(t3.getName());
        System.out.println(t4.getName());
        Thread t5 = new Thread(() -> {
            System.out.println("Runnable...." + Thread.currentThread().getName());
        }, "RunnableThread");
        System.out.println(t5.getName());
    }
}
