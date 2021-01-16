package com.ln.concurrency.chapter6;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter6
 * @version: 1.0
 */

import javafx.concurrent.Worker;

/**
 * @ClassName:ThreadCloseforce
 * @Author:linianest
 * @CreateTime:2020/3/14 21:22
 * @version:1.0
 * @Description TODO: 调用任务线程
 */
public class ThreadCloseForce {


    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();
        service.execute(() -> {
            System.out.println("task running..");
            // load a very heavy resource.
//            while (true) {
//            }
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown(10_000);
        long end = System.currentTimeMillis();
        System.out.printf("start timestamp is:%s,\nend timestamp is:%s, \ntotal spend time is:%s (ms)\n", start, end, end - start);
    }
}
