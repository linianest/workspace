package com.ln.concurrency.chapter9;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter9
 * @version: 1.0
 */

import java.util.stream.Stream;

/**
 * @ClassName:DifferenceOfWaitAndSleep
 * @Author:linianest
 * @CreateTime:2020/3/16 16:27
 * @version:1.0
 * @Description TODO: sleep与wait区别
 */
public class DifferenceOfWaitAndSleep {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Stream.of("T1","T2").forEach(name->{
            new Thread(name){
                @Override
                public void run() {
//                    m1();
                    m2();
                }
            }.start();
        });
    }

    public static void m1() {
        synchronized (LOCK) {
            try {
                System.out.println("The thread " + Thread.currentThread().getName() + " entry.");
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void m2() {
        synchronized (LOCK) {
            try {
                System.out.println("The thread " + Thread.currentThread().getName() + " entry.");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
