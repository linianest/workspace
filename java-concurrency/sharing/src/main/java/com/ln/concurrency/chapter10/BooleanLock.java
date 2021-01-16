package com.ln.concurrency.chapter10;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter10
 * @version: 1.0
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @ClassName:BooleanLock
 * @Author:linianest
 * @CreateTime:2020/3/18 10:21
 * @version:1.0
 * @Description TODO: 自定义显示锁lock
 */
public class BooleanLock implements Lock {

    /**
     * The initValue is false indicated the lock have be get.
     * The initValue is false indicated the lock is free.(other thread can get it.)
     * 为true表示锁已经被获取了
     * 为false表示锁是闲着的，其他线程可以获取锁
     */
    private boolean initValue;

    private Thread currentThread;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();


    public BooleanLock() {
        this.initValue = false;
    }

    // the thread get lock
    @Override
    public synchronized void lock() throws InterruptedException {

        // if the lock had be got others thread
        while (initValue) {
            // add currentThread to monitorCollection and set thread status is wait.
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        // if currentThread get lock
        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue = true;

        currentThread = Thread.currentThread();
    }


    /**
     * 超时锁
     *
     * @param mills
     * @throws InterruptedException
     * @throws TimeOutException
     */
    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0)
            lock();
        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (initValue) {
            if (hasRemaining <= 0)
                throw new TimeOutException("Time out.");
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = endTime - System.currentTimeMillis();
        }
        // if currentThread get lock
        this.initValue = true;
        currentThread = Thread.currentThread();
    }

    // the thread release the lock(BooleanLock instance)
    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == currentThread) {
            this.initValue = false;
            Optional.of(Thread.currentThread().getName() + " release the lock monitor.")
                    .ifPresent(System.out::println);
            this.notifyAll();
        }

    }

    // get设置成只读，防止调用者修改集合
    @Override
    public Collection<Thread> getBlockThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockSize() {
        return blockedThreadCollection.size();
    }
}
