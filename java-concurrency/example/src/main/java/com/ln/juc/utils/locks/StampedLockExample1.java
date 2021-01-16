package com.ln.juc.utils.locks;

import jdk.nashorn.internal.runtime.options.Options;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.utils.locks
 * @Name:StampedLockExample
 * @Author:linianest
 * @CreateTime:2021/1/15 19:59
 * @version:1.0
 * @Description TODO: 悲观读写锁，读的线程多，写的线程少，写的比较“饥饿”
 */
public class StampedLockExample1 {


    /**
     * ReentrantLock vs Synchronized
     * <p>
     *
     * </p>
     * ReentrantReadWriteLock
     *
     * @param args
     */

    private final static StampedLock lock = new StampedLock();
    private final static List<Long> DATA = new ArrayList<>();

    public static void main(String[] args) {

        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        Runnable readTask = () -> {
            for (; ; ) {
                read();
            }
        };
        Runnable writeTask = () -> {
            for (; ; ) {
                write();
            }
        };
        IntStream.rangeClosed(1, 9).boxed()
                .forEach(i -> executorService.submit(readTask));
        executorService.submit(writeTask);
    }

    private static void read() {
        long stamped = -1;
        try {
            stamped = lock.readLock();
            Optional.of(DATA.stream().map(String::valueOf)
                    .collect(Collectors.joining("#", "R-", "")))
                    .ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamped);
        }
    }

    private static void write() {
        long stamped = -1;
        try {
            stamped = lock.writeLock();
            DATA.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamped);
        }
    }
}
