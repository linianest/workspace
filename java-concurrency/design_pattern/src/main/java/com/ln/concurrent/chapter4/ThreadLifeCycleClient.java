package com.ln.concurrent.chapter4;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter4
 * @version: 1.0
 */

import java.util.Arrays;

/**
 *@ClassName:ThreadLifeCycleClient
 *@Author:linianest
 *@CreateTime:2020/3/23 12:43
 *@version:1.0
 *@Description TODO: 多线程观察者模式
 */
public class ThreadLifeCycleClient {
    public static void main(String[] args) {
       new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1","2"));
    }
}
