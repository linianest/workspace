package com.ln.concurrent.chapter18;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter18
 * @Name:ActiveObjcetProxy
 * @Author:linianest
 * @CreateTime:2021/1/4 15:57
 * @version:1.0
 * @Description TODO:ActiveObjcet代理
 */
class ActiveObjcetProxy implements ActiveObject {

    private final SchedulerThread schedulerThread;
    private final Servant servant;

    public ActiveObjcetProxy(SchedulerThread schedulerThread, Servant servant) {
        this.schedulerThread = schedulerThread;
        this.servant = servant;
    }

    @Override
    public Result makeString(int count, char fillChar) {
        FutureResult futureResult = new FutureResult();
        schedulerThread.invoke(new MakeStringRequest(servant, futureResult, count, fillChar));
        return futureResult;
    }

    @Override
    public void displayString(String text) {
        schedulerThread.invoke(new DisplayStringRequest(servant, text));
    }
}
