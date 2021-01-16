package com.ln.concurrent.chapter4;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter4
 * @version: 1.0
 */

/**
 *@ClassName:ObservableRunnable
 *@Author:linianest
 *@CreateTime:2020/3/23 12:03
 *@version:1.0
 *@Description TODO: 可供观察的线程Runnable，提供线程的事件、状态、异常信息，反馈给观察者的onEvent抽象方法
 */
public abstract class ObservableRunnable implements Runnable {
    // listener是抽象类的观察者
    final protected LifeCycleListener listener;

    public ObservableRunnable(final LifeCycleListener listener){
        this.listener = listener;
    }
    // 事件源
    protected void notifyChange(final RunnableEvent event){
        listener.onEvent(event);
    }

    /**
     * 使用枚举定义线程状态（推荐经常使用）
     */
    public enum RunnableState{
        RUNNING,ERROR,DONE
    }

    /**
     * 自定义线程事件
     */
    public static class RunnableEvent{
          private final RunnableState state;
          private final Thread thread;
          private final Throwable cause;

        /**
         * @param state 线程状态
         * @param thread 线程
         * @param cause 线程异常
         */
        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }
}
