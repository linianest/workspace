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
public class SynchronizedStaticTest {
    public static void main(String[] args) {
        new Thread("T1") {
            @Override
            public void run() {
                SynchronizedStatic.m1();
            }
        }.start();
        new Thread("T2") {
            @Override
            public void run() {
                SynchronizedStatic.m2();
            }
        }.start();
        new Thread("T3") {
            @Override
            public void run() {
                SynchronizedStatic.m3();
            }
        }.start();
    }
}
