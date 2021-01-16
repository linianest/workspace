package com.ln.concurrent.chapter18;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter18
 * @Name:FutureResult
 * @Author:linianest
 * @CreateTime:2021/1/4 15:28
 * @version:1.0
 * @Description TODO: Future设计模式的结果
 */

/**
 * 获取结果的线程，如果结果没有出来，就wait住，如果结果出来就返回真的结果
 */
public class FutureResult implements Result {
    private Result result;
    private boolean ready=false;
    public synchronized void setResult(Result result){
        this.result=result;
        this.ready=true;
        this.notifyAll();
    }
    @Override
    public synchronized Object getResultValue() {
        while (!ready){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.result.getResultValue();
    }
}
