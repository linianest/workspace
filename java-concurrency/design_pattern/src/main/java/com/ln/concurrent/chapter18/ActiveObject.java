package com.ln.concurrent.chapter18;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter18
 * @Name:ActiveObject
 * @Author:linianest
 * @CreateTime:2021/1/4 15:09
 * @version:1.0
 * @Description TODO: 接受异步消息的主动对象
 */
public interface ActiveObject {
    Result  makeString(int count,char fillChar);

    void displayString(String text);
}
