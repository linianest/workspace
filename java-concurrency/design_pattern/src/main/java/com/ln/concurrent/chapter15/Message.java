package com.ln.concurrent.chapter15;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter15
 * @Name:Message
 * @Author:linianest
 * @CreateTime:2021/1/4 11:13
 * @version:1.0
 * @Description TODO: 请求数据
 */
public class Message {
    private final String value;

    public Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
