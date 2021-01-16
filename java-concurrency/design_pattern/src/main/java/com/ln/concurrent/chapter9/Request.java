package com.ln.concurrent.chapter9;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter9
 * @Name:Request
 * @Author:linianest
 * @CreateTime:2020/3/28 19:29
 * @version:1.0
 * @Description TODO: Request请求
 */
public class Request {
    private final String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
