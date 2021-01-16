package com.ln.concurrent.chapter18;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter18
 * @Name:ActiveObjectFactory
 * @Author:linianest
 * @CreateTime:2021/1/4 16:02
 * @version:1.0
 * @Description TODO:
 */
public final class ActiveObjectFactory {
    private ActiveObjectFactory() {

    }

    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        SchedulerThread schedulerThread = new SchedulerThread(queue);
        ActiveObjcetProxy proxy = new ActiveObjcetProxy(schedulerThread, servant);
        schedulerThread.start();
        return proxy;
    }
}
