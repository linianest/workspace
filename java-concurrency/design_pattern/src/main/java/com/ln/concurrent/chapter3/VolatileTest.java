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
 * @Description TODO: volatile关键字的使用
 */
public class VolatileTest {
    private volatile static int INIT_VALUE = 0;
    private final static int MAX_LIMIT = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                if (localValue != INIT_VALUE) {
                    System.out.printf("The value update to [%d]\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "READER").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("Update the value to [%d]\n", ++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(5_00);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATER").start();
    }
}
