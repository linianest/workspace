package com.ln.concurrent.chapter6;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter6
 * @version: 1.0
 */

import java.util.Random;

/**
 * @ClassName:ReadWorker
 * @Author:linianest
 * @CreateTime:2020/3/24 17:18
 * @version:1.0
 * @Description TODO: 多线程写数据
 */
public class ReadWorker extends Thread {


    private final SharedData data;

    public ReadWorker(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuf = data.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readBuf));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
