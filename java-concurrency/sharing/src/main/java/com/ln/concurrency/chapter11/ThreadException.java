package com.ln.concurrency.chapter11;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter11
 * @version: 1.0
 */

/**
 * @ClassName:ThreadException
 * @Author:linianest
 * @CreateTime:2020/3/18 17:34
 * @version:1.0
 * @Description TODO: 捕捉线程运行期间的异常
 */
public class ThreadException {
/*    private final static int A = 10;
    private final static int B = 0;*/

    public static void main(String[] args) {
        new Test1().test();

       /* Thread t = new Thread(() -> {
            try {
                Thread.sleep(3_000);
                int result = A / B;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        t.setUncaughtExceptionHandler((thread, e) -> {
            System.out.println(e);
            System.out.println(thread);
        });
        t.start();*/
    }

}
