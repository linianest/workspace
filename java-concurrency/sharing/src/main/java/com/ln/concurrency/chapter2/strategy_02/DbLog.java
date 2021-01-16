package com.ln.concurrency.chapter2.strategy_02;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2.strategy_02
 * @Name:DbLog
 * @Author:linianest
 * @CreateTime:2020/12/26 18:16
 * @version:1.0
 * @Description TODO:
 */
/**

 * 把日志记录到数据库

 */

public class DbLog implements LogStrategy{

    public void log(String msg) {

        //制造错误

        if(msg!=null && msg.trim().length()>5){

            int a = 5/0;

        }

        System.out.println("现在把 '"+msg+"' 记录到数据库中");

    }

}