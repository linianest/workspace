package com.ln.concurrency.chapter3;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter3
 * @version: 1.0
 */

/**
 * @ClassName:CreateThread5
 * @Author:linianest
 * @CreateTime:2020/3/14 15:46
 * @version:1.0
 * @Description TODO: 调整线程宽度
 */
public class CreateThread5 {
    private static int counter = 1;

    public static void main(String[] args) {
        try {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                counter++;
                new Thread(() -> {
                    byte[] data = new byte[1024 * 1024 * 2];
                    while (true) {
//                        try {
//                            Thread.sleep(1);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }).start();
            }
        } catch (Error e) {
        }

        System.out.println("Total created thread nums=>" + counter);
    }
}
