package com.ln.concurrency.chapter7;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter7
 * @version: 1.0
 */

/**
 * @ClassName:SynchronizedStaticTest
 * @Author:linianest
 * @CreateTime:2020/3/16 10:04
 * @version:1.0
 * @Description TODO:
 */
public class SynchronizedStatic {

    static {
        synchronized (SynchronizedStatic.class) {
            System.out.println("static " + Thread.currentThread().getName());
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 静态方法和静态代码块使用的锁是class锁
    public synchronized static void m1() {
        System.out.println("m1 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2() {
        System.out.println("m2 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m3() {
        System.out.println("m3" + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
