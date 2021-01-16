package com.ln.concurrent.chapter4;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter4
 * @Name:LifeCycleListener
 * @Author:linianest
 * @CreateTime:2021/1/1 17:30
 * @version:1.0
 * @Description TODO: 定义观察者公共接口
 */
public interface LifeCycleListener {
    // todo 观察者观察线程状态
    void onEvent(ObservableRunnable.RunnableEvent event);
}
