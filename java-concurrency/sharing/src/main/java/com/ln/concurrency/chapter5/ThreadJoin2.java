package com.ln.concurrency.chapter5;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter5
 * @version: 1.0
 */

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @ClassName:ThreadJoin2
 * @Author:linianest
 * @CreateTime:2020/3/14 17:56
 * @version:1.0
 * @Description TODO:
 */
public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {

            try {
                System.out.println("t1 is running.");
                Thread.sleep(10_000);
                System.out.println("t1 is done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        // 父线程等待子线程执行100毫秒之后，开始执行
        t1.join(100);
        // 父线程等待子线程执行1000毫秒之后,再等待10纳秒，开始执行
//        t1.join(1000, 10);

        Optional.of("All of tasks finish done.").ifPresent(System.out::println);

        IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));

        // start httpServer
        // 有的时候，服务启动起来就挂掉，有可能是该服务线程设置成守护线程
        // JettyHttpServer.start();
        // main 会一直处于运行状态
        Thread.currentThread().join();
    }
}
