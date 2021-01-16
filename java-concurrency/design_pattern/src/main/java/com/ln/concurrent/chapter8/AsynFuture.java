package com.ln.concurrent.chapter8;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter8
 * @Name:AsynFuture
 * @Author:linianest
 * @CreateTime:2020/3/25 15:04
 * @version:1.0
 * @Description TODO: 异步future实现
 */
public class AsynFuture<T> implements Future<T> {
    private volatile boolean done = false;
    private T result;

    public void done(T result) {
        synchronized (this) {
            this.result = result;
            this.done = true;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (!done) {
                this.wait();
            }
        }
        return result;
    }
}
