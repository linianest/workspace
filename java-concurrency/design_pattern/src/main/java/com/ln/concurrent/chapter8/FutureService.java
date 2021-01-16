package com.ln.concurrent.chapter8;

import java.util.function.Consumer;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter8
 * @Name:FutureService
 * @Author:linianest
 * @CreateTime:2020/3/25 15:01
 * @version:1.0
 * @Description TODO: 实现连接future接口和future实现的接口
 */

public class FutureService {
    // todo 被动模式：返回结果后,需要再次调用Future的get方法获取数据
    public <T> Future<T> submit(final FutureTask<T> task) {
        AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(() -> {
            T result = task.call();
            asynFuture.done(result);
        }).start();
        return asynFuture;
    }

    // todo 主动模式：任务执行完成后,将结果传递给给指定的方法
    public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer) {
        AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(() -> {
            T result = task.call();
            // done方法时被动模式的，兼容的方式下，留着
//            asynFuture.done(result);
            consumer.accept(result);
        }).start();
        return asynFuture;
    }
}
