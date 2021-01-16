package com.ln.concurrency.chapter8;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter8
 * @version: 1.0
 */

/**
 * @ClassName:OtherService
 * @Author:linianest
 * @CreateTime:2020/3/16 11:05
 * @version:1.0
 * @Description TODO:
 */
public class OtherService {
    private Object lock = new Object();

    private DeadLock deadLock;

    public void s1() {
        synchronized (lock) {
            System.out.println("s1===========");
        }
    }

    public void s2() {
        synchronized (lock) {
            System.out.println("s2============");
            deadLock.m2();
        }
    }

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }
}
