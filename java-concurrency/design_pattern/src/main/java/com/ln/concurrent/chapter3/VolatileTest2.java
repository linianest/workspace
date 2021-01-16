package com.ln.concurrent.chapter3;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter3
 * @version: 1.0
 */

/**
 * @ClassName:VolatileTest
 * @Author:linianest
 * @CreateTime:2020/3/22 12:08
 * @version:1.0
 * @Description TODO:
 */
public class VolatileTest2 {
    private static int INIT_VALUE = 0;
    private final static int MAX_LIMIT = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println("T1->" + (++INIT_VALUE));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();

        new Thread(() -> {
            System.out.println("T1->" + (++INIT_VALUE));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ADDER-2").start();
    }
}
