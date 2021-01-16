package com.ln.concurrency.chapter6;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter6
 * @version: 1.0
 */

/**
 * @ClassName:ThreadService
 * @Author:linianest
 * @CreateTime:2020/3/15 9:12
 * @version:1.0
 * @Description TODO: 编写ThreadService，实现暴力结束线程
 */
public class ThreadService {

    private Thread executeThread;
    private boolean finished = false;

    /**
     * 1.执行线程通过创建一个守护线程的方式，
     * 当执行线程结束，守护线程也结束,实现了结束线程
     *
     * @param task
     */
    public void execute(Runnable task) {
        this.executeThread = new Thread() {
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);

                runner.start();
                try {
                    // 设置当前线程是join，免得父线程执行完毕就挂掉，导致该线程无法执行任务
                    runner.join();
                    // 任务执行完成,设置成true
                    finished = true;
                    System.out.println("任务执行完成！");
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println("任务线程被中断！");
                }
            }
        };

        executeThread.start();
    }

    /**
     * 超时关闭线程
     *
     * @param mills
     */
    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        // 如果没有执行完成
        while (!finished) {
            // 如果超时未完成任务
            if ((System.currentTimeMillis() - currentTime) >= mills) {
                System.out.println("任务超时，需要结束任务线程！");
                // 中断执行线程
                executeThread.interrupt();
                break;
            }
            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被中断！");
                break;
            }
        }
        finished = false;
    }
}
