package com.ln.concurrency.chapter7;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter7
 * @version: 1.0
 */

/**
 * @ClassName:Synchronized
 * @Author:linianest
 * @CreateTime:2020/3/15 15:08
 * @version:1.0
 * @Description TODO:
 */
public class SynchronizedTest {

    private final static Object LOCK = new Object();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            synchronized (LOCK) {
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();
    }
}
