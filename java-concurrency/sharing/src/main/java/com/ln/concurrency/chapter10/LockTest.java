package com.ln.concurrency.chapter10;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter10
 * @version: 1.0
 */

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @ClassName:LockTest
 * @Author:linianest
 * @CreateTime:2020/3/18 10:34
 * @version:1.0
 * @Description TODO: 多线程自定义lock测试
 */
public class LockTest {


    public static void main(String[] args) throws InterruptedException {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4")
                .forEach(name -> {
                    new Thread(() -> {
                        try {
                            booleanLock.lock(10L);
                            Optional.of(Thread.currentThread().getName() + " have the lock Monitor.")
                                    .ifPresent(System.out::println);
                            work();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (Lock.TimeOutException e) {
                            Optional.of(Thread.currentThread().getName() + " time out.")
                                    .ifPresent(System.out::println);
                        } finally {
                            booleanLock.unlock();
                        }
                    }, name).start();
                });
        Thread.sleep(100);
        booleanLock.unlock();
    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " is Working...")
                .ifPresent(System.out::println);
        Thread.sleep(3_000);
    }
}
