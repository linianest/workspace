package com.ln.concurrency.chapter2.strategy_02;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2.strategy_02
 * @Name:Client
 * @Author:linianest
 * @CreateTime:2020/12/26 18:26
 * @version:1.0
 * @Description TODO:
 */
public class Client {

    public static void main(String[] args) {
        LogContxt logContxt = new LogContxt();
        // todo 正常执行程序
        logContxt.log("记录日志");
        // todo 正常执行程序，如果发现错误，就执行容错恢复机制
        logContxt.log("再次记录日志");

    }
}
