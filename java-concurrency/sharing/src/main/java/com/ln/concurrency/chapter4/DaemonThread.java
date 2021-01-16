package com.ln.concurrency.chapter4;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter4
 * @version: 1.0
 */

/**
 * @ClassName:DeamonThread
 * @Author:linianest
 * @CreateTime:2020/3/14 16:20
 * @version:1.0
 * @Description TODO: 守护线程
 */
public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "running.");
                    Thread.sleep(30_000L);
                    System.out.println(Thread.currentThread().getName() + "done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }; // new

        // 设置守护线程，当main线程生命周期结束时，该线程也会跟着结束，而不管是否任务执行完成。
        t.setDaemon(true);

        // runnable->running|->dead|->blocked
        t.start();
        Thread.sleep(5_000); //jdk1.7
        System.out.println(Thread.currentThread().getName());
    }
}
