package com.ln.juc.executors;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:TimeScheduler
 * @Author:linianest
 * @CreateTime:2021/1/7 16:26
 * @version:1.0
 * @Description TODO: 时间调度器
 */
public class TimeScheduler {
    /**
     * 任务一秒后执行，每隔一秒执行一次
     * @param args
     */
    public static void main(String[] args) {

        final Timer timer = new Timer();
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("============" + System.currentTimeMillis());
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }
}
