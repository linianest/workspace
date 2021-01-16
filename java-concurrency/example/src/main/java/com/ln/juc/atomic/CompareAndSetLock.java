package com.ln.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.jcu.atomic
 * @Name:CompareAndSetLock
 * @Author:linianest
 * @CreateTime:2021/1/6 22:55
 * @version:1.0
 * @Description TODO: 利用Atomic原子型CompareAndSet设计一个trylock
 */
public class CompareAndSetLock {

    // 设置初始值为0
    private static AtomicInteger value = new AtomicInteger(0);

    private Thread localThread;


    public void tryLock() throws GetLockExecuteion {
        // 如果获取到的值为0，代表获取数据的时间内，数据没有被其他线程操作，设置为1
        final boolean success = value.compareAndSet(0, 1);
        if (!success) {
            throw new GetLockExecuteion("get the lock failed.");
        } else {
            localThread = Thread.currentThread();
        }
    }

    public void unLock() {
        if (0 == value.get()) {
            return;
        }
        // 不是加锁的线程不能释放锁
        if (localThread == Thread.currentThread())
            value.compareAndSet(1, 0);
    }

    public static class GetLockExecuteion extends Exception {
        public GetLockExecuteion() {
            super();
        }

        public GetLockExecuteion(String message) {
            super(message);
        }
    }
}
