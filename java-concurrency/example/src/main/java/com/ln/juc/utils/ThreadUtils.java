package com.ln.juc.utils;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors.utils
 * @Name:ThreadUtils
 * @Author:linianest
 * @CreateTime:2021/1/10 23:10
 * @version:1.0
 * @Description TODO: 线程工具类
 */
public class ThreadUtils {


    /**
     * todo sleep the specify SECONDS
     *
     * @param SECONDS
     */
    public static void sleep(long SECONDS) {
        try {
            TimeUnit.SECONDS.sleep(SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * todo sleep the specify MILLISECONDS
     *
     * @param MILLISECONDS
     */
    public static void sleepMILLISECONDS(long MILLISECONDS) {
        try {
            TimeUnit.MILLISECONDS.sleep(MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
