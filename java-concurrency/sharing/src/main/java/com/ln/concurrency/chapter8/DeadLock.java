package com.ln.concurrency.chapter8;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter8
 * @version: 1.0
 */

/**
 * @ClassName:DeadLock
 * @Author:linianest
 * @CreateTime:2020/3/16 11:04
 * @version:1.0
 * @Description TODO:
 */
public class DeadLock {
    private OtherService otherService;

    private Object lock = new Object();

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    public void m1() {
        synchronized (lock) {
            System.out.println("m1");
            otherService.s1();
        }
    }

    public void m2() {
        synchronized (lock) {
            System.out.println("m2");
        }
    }
}
