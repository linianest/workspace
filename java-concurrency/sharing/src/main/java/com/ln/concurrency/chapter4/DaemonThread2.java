package com.ln.concurrency.chapter4;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter4
 * @version: 1.0
 */

/**
 * @ClassName:DaemonThread2
 * @Author:linianest
 * @CreateTime:2020/3/14 16:45
 * @version:1.0
 * @Description TODO: 守护线程是否能传递
 */
public class DaemonThread2 {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Thread innerThread = new Thread(() -> {
                try {
                    while (true){
                        System.out.println("Do some thing for health check");
                        Thread.sleep(3_000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            innerThread.start();
            /**1.设置成daemon线程，当主线程死亡退出，该线程也会退出。
             * java.lang.IllegalThreadStateException 表示线程已经启动，如果再设置成守护线程就会抛异常
             * setDaemon(true);必须在start方法之前调用
             */

            innerThread.setDaemon(true);

            try {
                Thread.sleep(1_000);
                System.out.println("t thread finish done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        t.setDaemon(true);
        t.start();
    }
}
