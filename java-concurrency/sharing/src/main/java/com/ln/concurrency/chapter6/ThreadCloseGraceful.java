package com.ln.concurrency.chapter6;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter6
 * @version: 1.0
 */

/**
 * @ClassName:ThreadCloseGraceful
 * @Author:linianest
 * @CreateTime:2020/3/14 20:51
 * @version:1.0
 * @Description TODO: 通过标志位中断线程
 */
public class ThreadCloseGraceful {

    private static class Worker extends Thread {
        private volatile boolean start = true;

        @Override
        public void run() {
            while (start) {
                //
            }
        }

        public void shutdown() {
            this.start = false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.shutdown();
    }
}
