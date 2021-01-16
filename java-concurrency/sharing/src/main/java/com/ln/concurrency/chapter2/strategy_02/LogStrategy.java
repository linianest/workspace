package com.ln.concurrency.chapter2.strategy_02;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2.strategy_02
 * @Name:LogStrategy
 * @Author:linianest
 * @CreateTime:2020/12/26 18:15
 * @version:1.0
 * @Description TODO:先定义日志策略接口，很简单，就是一个记录日志的方法
 */

/**

 * 日志记录策略的接口

 */
public interface LogStrategy {

    /**

     * 记录日志

     * @param msg 需记录的日志信息

     */

    public void log(String msg);
}
