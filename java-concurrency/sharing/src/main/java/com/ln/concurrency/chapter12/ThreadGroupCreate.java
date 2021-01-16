package com.ln.concurrency.chapter12;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter12
 * @version: 1.0
 */

import java.util.Arrays;

/**
 * @ClassName:ThreadGroupCreate
 * @Author:linianest
 * @CreateTime:2020/3/20 11:54
 * @version:1.0
 * @Description TODO: 线程组
 */
public class ThreadGroupCreate {
    public static void main(String[] args) {

        // use the name

        ThreadGroup tg1 = new ThreadGroup("tg1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                try {
                    System.out.println(getThreadGroup().getName());
                    System.out.println(getThreadGroup().getParent());
                    System.out.println(getThreadGroup().getParent().activeCount());
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        tg1.setDaemon(true);
        t1.start();
        System.out.println(tg1.isDestroyed());
        tg1.destroy();
        System.out.println(tg1.isDestroyed());


        // tg1与tg2有共同的线程组main
        ThreadGroup tg2 = new ThreadGroup("tg2");
        Thread t2 = new Thread(tg2, "T2") {
            @Override
            public void run() {
                System.out.println(tg1.getName());
                Thread[] threads = new Thread[tg1.activeCount()];
                tg1.enumerate(threads);
                Arrays.asList(threads).forEach(System.out::println);
            }
        };
        t2.start();
        System.out.println(tg1.activeCount());
        System.out.println(tg1.activeGroupCount());
        t2.checkAccess();

        System.out.println("=========================");
        Thread[] ts1 = new Thread[tg1.activeCount()];
        tg1.enumerate(ts1);
        Arrays.asList(ts1).forEach(System.out::println);

        System.out.println("==========false将所有的子线程获取出来,true只取两级===========");
        tg1.enumerate(ts1, true);
        Arrays.asList(ts1).forEach(System.out::println);

        tg1.interrupt();


        // use the parent and group name

//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getThreadGroup().getName());

    }
}
