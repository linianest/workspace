package com.ln.concurrency.chapter10;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter10
 * @version: 1.0
 */

import java.util.Collection;

/**
 * @ClassName:Lock
 * @Author:linianest
 * @CreateTime:2020/3/18 9:35
 * @version:1.0
 * @Description TODO: 多线程自定义lock接口
 */
public interface Lock {

    // 超时异常
    class TimeOutException extends Exception {
        public TimeOutException(String message) {
            super(message);
        }
    }

    // 锁
    void lock() throws InterruptedException;

    // 超时锁
    void lock(long mills) throws InterruptedException, TimeOutException;

    // 解锁
    void unlock();

    // 观察者
    Collection<Thread> getBlockThread();

    // 获取锁住的线程大小
    int getBlockSize();


}
