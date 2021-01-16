package com.ln.concurrency.chapter8;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter8
 * @version: 1.0
 */

/**
 * @ClassName:DeadLockTest
 * @Author:linianest
 * @CreateTime:2020/3/16 11:23
 * @version:1.0
 * @Description TODO: 分析死锁：jps查看进程号，使用jstack 进程号，可以查看死锁信息
 */
public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);

        otherService.setDeadLock(deadLock);

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    deadLock.m1();
                }

            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    otherService.s2();
                }

            }
        }.start();

    }

}
