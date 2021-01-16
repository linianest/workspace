package com.ln.concurrency.chapter3;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter3
 * @version: 1.0
 */

/**
 * @ClassName:CreateThread4
 * @Author:linianest
 * @CreateTime:2020/3/14 14:52
 * @version:1.0
 * @Description TODO:
 */
public class CreateThread4 {
    private static int counter = 0;

    // JVM will create a thread named main.
    public static void main(String[] args) {
        // create a JVM stack
        Thread t = new Thread(null, new Runnable() {
            @Override
            public void run() {
                try {
                    add(1);
                } catch (Error e) {
                    System.out.println(counter);
                }
            }

            //默认：17750，调大后：999431
            private void add(int i) {
                ++counter;
                add(i + 1);
            }
        }, "Test", 1 << 24);
        t.start();
    }
}
