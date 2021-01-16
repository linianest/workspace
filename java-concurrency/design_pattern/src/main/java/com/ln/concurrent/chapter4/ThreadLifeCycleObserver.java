package com.ln.concurrent.chapter4;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter4
 * @version: 1.0
 */

import java.util.List;

/**
 * @ClassName:ThreadLifeCycleObserver
 * @Author:linianest
 * @CreateTime:2020/3/23 12:12
 * @version:1.0
 * @Description TODO: 实现多线程观察者实现接口，当有线程状态发生改变时，反馈给onEvent方法
 */
public class ThreadLifeCycleObserver implements LifeCycleListener {

    private final Object LOCK = new Object();

    /**
     * 当事件队列有事件时，每个事件都会启动一个线程去获取事件信息
     *
     * @param ids
     */
    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty())
            return;
        /**
         * todo this表示当前类是观察者
         * notifyChange 方法时runnable接口的方法，提供事件状态信息
         */

        ids.stream().forEach(id -> new Thread(new ObservableRunnable(this) {
            @Override
            public void run() {
                try {

                    notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("query for the id " + id);
                    Thread.sleep(1_000L);
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (Exception e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                }
            }
        }, id).start());
    }

    /**
     * Runnable 回调事件
     *
     * @param event
     */
    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {

        synchronized (LOCK) {
            System.out.println("The runnable [" + event.getThread().getName() + "] data changed and state is [" + event.getState() + "]");
            if (event.getCause() != null) {
                System.out.println("The runnable [" + event.getThread().getName() + "] process failed.");
                event.getCause().printStackTrace();
            }
        }
    }
}
