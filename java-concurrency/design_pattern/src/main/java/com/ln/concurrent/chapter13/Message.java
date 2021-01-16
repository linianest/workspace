package com.ln.concurrent.chapter13;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter13
 * @Name:Message
 * @Author:linianest
 * @CreateTime:2021/1/3 17:38
 * @version:1.0
 * @Description TODO: 消息体
 */
public class Message {

    private String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
