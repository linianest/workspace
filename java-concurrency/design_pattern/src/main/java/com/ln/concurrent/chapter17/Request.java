package com.ln.concurrent.chapter17;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter17
 * @Name:Request
 * @Author:linianest
 * @CreateTime:2021/1/4 14:05
 * @version:1.0
 * @Description TODO: 请求任务
 */
public class Request {
    private final String name;
    private final int number;

    public Request(final String name,final int number) {
        this.name = name;
        this.number = number;
    }

    public void exeute(){
        System.out.println(Thread.currentThread().getName()+" executed "+this);
    }
    @Override
    public String toString() {
        return "Request=> NO. "+number+" Name. "+name;
    }
}
