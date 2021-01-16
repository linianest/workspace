package com.ln.concurrency.chapter2.strategy_02;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2.strategy_02
 * @Name:LogContxt
 * @Author:linianest
 * @CreateTime:2020/12/26 18:18
 * @version:1.0
 * @Description TODO: 策略的上下文，注意这次是在上下文里面实现具体策略算法的选择，所以不需要客户端来指定具体的策略算法了
 */

/**
 * 日志记录的上下文
 */
public class LogContxt {

    /**
     * 记录日志的方法提供给客户端使用
     * @param msg 需要的日志信息
     */
    public void log(String msg){
        // 在上下文里面，自行实现对策略的方法
        //优先使用策略：记录到日志数据库
        LogStrategy strategy = new DbLog();
        try {
            strategy.log(msg);
        } catch (Exception e) {
            // 出错了，那么就记录到文件中
            strategy=new FileLog();
            strategy.log(msg);
        }
    }
}
