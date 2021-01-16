package com.ln.concurrency.chapter4;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter4
 * @version: 1.0
 */

import java.util.Optional;

/**
 *@ClassName:ThreadSimpleAPI
 *@Author:linianest
 *@CreateTime:2020/3/14 17:11
 *@version:1.0
 *@Description TODO: 线程API
 */
public class ThreadSimpleAPI {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(30_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t.start();
        Optional.of(t.getName()).ifPresent(System.out::println);
        Optional.of(t.getId()).ifPresent(System.out::println);
        Optional.of(t.getPriority()).ifPresent(System.out::println);
    }
}
