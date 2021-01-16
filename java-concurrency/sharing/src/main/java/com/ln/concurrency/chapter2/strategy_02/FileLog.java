package com.ln.concurrency.chapter2.strategy_02;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2.strategy_02
 * @Name:FileLog
 * @Author:linianest
 * @CreateTime:2020/12/26 18:17
 * @version:1.0
 * @Description TODO: 实现记录日志到文件中去
 */
/**

 * 把日志记录到文件

 */

public class FileLog implements LogStrategy{

    public void log(String msg) {

        System.out.println("现在把 '"+msg+"' 记录到文件中");

    }

}
