package com.ln.concurrent.chapter6;

/**
 * @ClassName:ReadWriteLock
 * @Author:linianest
 * @CreateTime:2020/3/24 11:06
 * @version:1.0
 * @Description TODO: 读写锁
 */

/**
 * 可以并行多线读，如果是写线程操作，写的线程每次只允许一个
 */
public class ReadWriteLock {
    // 正在读
    private int readingReaders = 0;
    // 等待读
    private int waitingReaders = 0;
    // 正在写
    private int writingwriters = 0;
    // 等待写
    private int waitingwriters = 0;

    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    /**
     * 读锁：如果有线程正在写，那么就wait
     *
     * @throws InterruptedException
     */
    public synchronized void readLock() throws InterruptedException {
        // 有线程在准备读
        this.waitingReaders++;
        try {
            // 有线程正在写
            while (writingwriters > 0 || (preferWriter && waitingwriters > 0)) {
                this.wait();
            }
            // 现在正在读
            this.readingReaders++;
        } finally {
            // 线程读完了
            this.waitingReaders--;
        }
    }

    /**
     * 释放读锁
     */
    public synchronized void readUnlock() {
        this.readingReaders--;
        this.notifyAll();
    }

    /**
     * 写锁：如果有线程在读或者在写，那么就wait
     *
     * @throws InterruptedException
     */
    public synchronized void writeLock() throws InterruptedException {
        this.waitingwriters++;
        try {
            while (readingReaders > 0 || writingwriters > 0) {
                this.wait();
            }
            this.writingwriters++;
        } finally {
            this.waitingwriters--;
        }
    }

    /**
     * 释放写锁
     */
    public synchronized void writeUnlock() {
        this.writingwriters--;
        this.notifyAll();
    }

}
