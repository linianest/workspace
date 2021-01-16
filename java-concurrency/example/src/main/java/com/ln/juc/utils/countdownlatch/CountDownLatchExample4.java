package com.ln.juc.utils.countdownlatch;

import com.ln.juc.utils.ThreadUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.utils.countdownlatch
 * @Name:CountDownLatchExample3
 * @Author:linianest
 * @CreateTime:2021/1/16 13:10
 * @version:1.0
 * @Description TODO: CountDownLatch API
 */

public class CountDownLatchExample4 {


    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch lath = new CountDownLatch(1);
        Thread mainThread = Thread.currentThread();

        new Thread() {
            @Override
            public void run() {
                ThreadUtils.sleep(3);
                // 在执行countdown之前，await的线程不能释放
                mainThread.interrupt();
                lath.countDown();
            }
        }.start();
        lath.await(1000, TimeUnit.MILLISECONDS);
        System.out.println("==============");
    }


}
