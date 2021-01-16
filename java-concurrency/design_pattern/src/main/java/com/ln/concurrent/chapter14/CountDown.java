package com.ln.concurrent.chapter14;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter14
 * @Name:CountDown
 * @Author:linianest
 * @CreateTime:2021/1/4 9:28
 * @version:1.0
 * @Description TODO: 自定义计数器
 */
public class CountDown {
    private final int total;
    private int counter;

    public CountDown(int total) {
        this.total = total;
    }
    public void down(){
        synchronized (this){
            this.counter++;
            this.notifyAll();
        }
    }

    public void await(){
        synchronized (this){
            while (counter!=total){
                this.await();
            }
        }
    }
}
