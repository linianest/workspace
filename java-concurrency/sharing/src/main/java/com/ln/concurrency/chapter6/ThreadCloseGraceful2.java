package com.ln.concurrency.chapter6;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter6
 * @version: 1.0
 */

/**
 * @ClassName:ThreadCloseGraceful2
 * @Author:linianest
 * @CreateTime:2020/3/14 21:04
 * @version:1.0
 * @Description TODO: 通过捕捉线程中断信号,退出线程
 */
public class ThreadCloseGraceful2 {
    private static class Worker extends Thread {

        @Override
        public void run() {
            while (true) {
                // 如果线程是被中断的状态，退出线程
                if (Thread.interrupted())
                    break;
            }
            //--------------------
        }

    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.interrupt();
    }
}
