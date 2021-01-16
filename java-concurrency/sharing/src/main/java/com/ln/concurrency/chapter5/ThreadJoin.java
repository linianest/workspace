package com.ln.concurrency.chapter5;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter5
 * @version: 1.0
 */

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @ClassName:ThreadJoin
 * @Author:linianest
 * @CreateTime:2020/3/14 17:40
 * @version:1.0
 * @Description TODO:
 */

/**
 * IntStream: java8的数据流模式
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
        });
        Thread t2 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
        });
        t1.start();
        t2.start();
        /**
         *线程设置成join方法：设置成join的线程执行结束，父线程才会执行，必须放在start之后
         * t1与t2是并行交互式进行执行的
         */
        t1.join();
        t2.join();


        Optional.of("All of tasks finish done.").ifPresent(System.out::println);

        IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
    }
}
