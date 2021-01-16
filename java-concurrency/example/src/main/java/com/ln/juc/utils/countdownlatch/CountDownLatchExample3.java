package com.ln.juc.utils.countdownlatch;

import com.ln.juc.utils.ThreadUtils;

import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.utils.countdownlatch
 * @Name:CountDownLatchExample3
 * @Author:linianest
 * @CreateTime:2021/1/16 13:10
 * @version:1.0
 * @Description TODO: 串行任务中，并行执行子任务中
 */

/**
 * 初始化任务，一个任务需要数据，在执行的过程中，另外一个线程去获取数据
 */
public class CountDownLatchExample3 {

    private static final CountDownLatch lath = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                System.out.println("Do some initial working.....");
                try {
                    ThreadUtils.sleep(1);
                    lath.await();
                    System.out.println("Do other working.....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                try {
                    lath.await();
                    System.out.println("release.....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                System.out.println("async prepare for some data.....");
                try {
                    ThreadUtils.sleep(2);
                    System.out.println("data prepare for done.");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lath.countDown();
                }
            }
        }.start();

        Thread.currentThread().join();

    }


}
