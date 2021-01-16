package com.ln.concurrency.chapter10;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter10
 * @version: 1.0
 */

/**
 * @ClassName:SynchronizedProblem
 * @Author:linianest
 * @CreateTime:2020/3/18 11:25
 * @version:1.0
 * @Description TODO: Synchronized问题
 */
public class SynchronizedProblem {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                SynchronizedProblem.run();
            }
        };
        t1.start();
        Thread.sleep(1000);

        Thread t2 = new Thread() {
            @Override
            public void run() {

                SynchronizedProblem.run();
            }
        };
        t2.start();
        Thread.sleep(2_000);
        t2.interrupt();
        System.out.println(t2.isInterrupted());
    }

    public synchronized static void run() {
        System.out.println(Thread.currentThread());
        while (true) {

        }
    }
}
