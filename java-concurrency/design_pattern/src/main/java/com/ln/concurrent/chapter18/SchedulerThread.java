package com.ln.concurrent.chapter18;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter18
 * @Name:SchedulerThread
 * @Author:linianest
 * @CreateTime:2021/1/4 15:50
 * @version:1.0
 * @Description TODO:
 */
public class SchedulerThread extends Thread {
    private final ActivationQueue activationQueue;


    public SchedulerThread(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }

    public  void invoke(MethodRequest request){
        this.activationQueue.put(request);
    }

    @Override
    public void run() {
        while (true){
            activationQueue.take().execute();
        }
    }
}
