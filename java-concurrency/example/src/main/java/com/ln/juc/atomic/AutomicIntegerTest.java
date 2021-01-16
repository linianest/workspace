package com.ln.juc.atomic;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.jcu.atomic
 * @Name:AutomicIntegerTest
 * @Author:linianest
 * @CreateTime:2021/1/6 21:21
 * @version:1.0
 * @Description TODO:原子型Integer
 */
public class AutomicIntegerTest {

    /**
     * volatile：特性
     * 1:内存可见性
     * 2：禁止重排序
     * 3：不能保证原子性
     */
    private volatile static int value;
    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());
    private static CompareAndSetLock lock = new CompareAndSetLock();

    public static void main(String[] args) {

        //非原子性操作
//        AutomicIntegerTest.UnAutomic();
        //原子性操作
//        AutomicIntegerTest.Automic();
        // 测试trylock
        AutomicIntegerTest.testTryLock();

    }

    public static void testTryLock() {
        IntStream.rangeClosed(1, 3).forEach(i -> {

            new Thread(() -> {

                try {
                    doSomething2();
                } catch (InterruptedException | CompareAndSetLock.GetLockExecuteion e) {
                    e.printStackTrace();
                }
            }).start();
        });

    }

    public static void doSomething() throws InterruptedException {
        synchronized (AutomicIntegerTest.class) {
            System.out.println(Thread.currentThread().getName() + " get the lock.");
            Thread.sleep(100_00);
        }
    }

    public static void doSomething2() throws InterruptedException, CompareAndSetLock.GetLockExecuteion {
        try {
            lock.tryLock();
            System.out.println(Thread.currentThread().getName() + " get the lock.");
            Thread.sleep(100_00);
        } finally {
            lock.unLock();
        }
    }

    /**
     * 原子性操作
     */
    public static void Automic() {
        List<Thread> list = new ArrayList<>();
        // 通过AtomicInteger获取的Integer数据类型具有原子型，不怕多线程骚扰了
        final AtomicInteger value = new AtomicInteger();
        IntStream.rangeClosed(1, 3).forEach(i -> {

            Thread thread = new Thread(() -> {

                int x = 0;
                while (x < 50) {
                    int temp = value.get();
                    set.add(temp);
                    System.out.println(Thread.currentThread().getName() + ":" + temp);
                    value.getAndIncrement();
                    x++;
                }
            });
            thread.start();
            list.add(thread);
        });
        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(set.size());
    }

    /**
     * 非原子性操作
     */
    public static void UnAutomic() {
        List<Thread> list = new ArrayList<>();
        IntStream.rangeClosed(1, 3).forEach(i -> {

            Thread thread = new Thread(() -> {

                int x = 0;
                while (x < 50) {
                    set.add(value);
                    int temp = value;
                    System.out.println(Thread.currentThread().getName() + ":" + temp);
                    value++;
                    x++;
                }
            });
            thread.start();
            list.add(thread);
        });
        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(set.size());
    }
}
