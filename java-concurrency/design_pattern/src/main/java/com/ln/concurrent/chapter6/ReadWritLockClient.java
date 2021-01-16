package com.ln.concurrent.chapter6;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter6
 * @version: 1.0
 * @ClassName:ReadWritLockClient
 * @Author:linianest
 * @CreateTime:2020/3/24 17:24
 * @version:1.0
 * @Description TODO: 读写锁
 */

/**
 *@ClassName:ReadWritLockClient
 *@Author:linianest
 *@CreateTime:2020/3/24 17:24
 *@version:1.0
 *@Description TODO: 读写锁设计模式
 */

/**
 * ReadWriteLock design pattern
 * Reader-Writer design pattern
 */
public class ReadWritLockClient {
    public static void main(String[] args) {
        final SharedData sharedData = new SharedData(10);
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new WriterWorker(sharedData, "sdfaSDFWEFASewd").start();
        new WriterWorker(sharedData, "werewkjSDFSDFWflksd").start();

    }
}
