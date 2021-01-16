package com.ln.concurrency.chapter7;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter7
 * @version: 1.0
 */

/**
 * @ClassName:SynchronizedThis
 * @Author:linianest
 * @CreateTime:2020/3/15 18:30
 * @version:1.0
 * @Description TODO:
 */
public class SynchronizedThis {

    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();
        new Thread("T1") {
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();
        new Thread("T2") {
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();
    }
}

class ThisLock {
    private final Object LOCK = new Object();
    public synchronized void m1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void m2() {
        synchronized(LOCK) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
