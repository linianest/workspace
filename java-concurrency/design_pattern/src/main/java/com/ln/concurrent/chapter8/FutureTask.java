package com.ln.concurrent.chapter8;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter8
 * @Name:FutureTask
 * @Author:linianest
 * @CreateTime:2020/3/25 14:56
 * @version:1.0
 * @Description TODO:future 真正执行任务的接口
 */
public interface FutureTask<T> {
    // 线程会掉返回结果的实现
    T call();
}
