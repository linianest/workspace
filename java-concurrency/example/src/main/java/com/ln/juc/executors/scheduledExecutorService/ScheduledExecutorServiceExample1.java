package com.ln.juc.executors.scheduledExecutorService;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors.scheduledExecutorService
 * @Name:ScheduledExecutorServiceExample1
 * @Author:linianest
 * @CreateTime:2021/1/10 15:01
 * @version:1.0
 * @Description TODO:ScheduledExecutorService
 */

/**
 * scheduleWithFixedDelay:todo 定时：2秒后执行任务,无视任务花费多长时间，任务执行完成的那一刻，2秒后重新循环执行
 * scheduleAtFixedRate:todo 按固定的时间延迟，循环调度任务,参数中有要求两次执行的最小时间间隔
 */
public class ScheduledExecutorServiceExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        testScheduleAtFixedRate();
        testScheduleWithFixedDelay();
    }

    /**
     * //todo 定时：2秒后执行任务,无视任务花费多长时间，任务执行完成的那一刻，2秒后重新循环执行
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testScheduleWithFixedDelay() throws ExecutionException, InterruptedException {
        final ScheduledThreadPoolExecutor service = new ScheduledThreadPoolExecutor(2);
        // todo shutdown后，结束循环调用
        service.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        // 定时：2秒后执行任务,无视任务花费多长时间，任务执行完成的那一刻，2秒后重新循环执行
        final ScheduledFuture<?> scheduledFuture = service.scheduleWithFixedDelay(() -> System.out.println(Thread.currentThread().getName() + " : " + System.currentTimeMillis() + ": i will be running."), 1,2, TimeUnit.SECONDS);
        sleep(3);
        System.out.println(scheduledFuture.get());
    }
    /**
     * todo 按固定的时间延迟，循环调度任务,参数中有要求两次执行的最小时间间隔
     */
    private static void testScheduleAtFixedRate() throws ExecutionException, InterruptedException {
        final ScheduledThreadPoolExecutor service = new ScheduledThreadPoolExecutor(2);
        // todo 允许service被shutdown后，继续执行循环的任务，默认为false
        service.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        // 定时：2秒后执行任务
        final ScheduledFuture<?> schedule = service.schedule(() -> System.out.println("i will be invoked."), 2, TimeUnit.SECONDS);
        sleep(3);
        System.out.println(schedule.cancel(true));
        // 定时：2秒后执行任务
        final ScheduledFuture<Integer> future = service.schedule(() -> 3, 2, TimeUnit.SECONDS);
        System.out.println(future.get());
        // 定时：1秒后执行任务,每隔2秒执行1次
        AtomicLong interval = new AtomicLong(0L);
        final ScheduledFuture<?> scheduledFuture = service.scheduleAtFixedRate(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            if (interval.get() == 0) {
                System.out.printf("the first time trigger task at %d\n", currentTimeMillis);
            } else {
                System.out.printf("the actually spend [%d]\n", currentTimeMillis - interval.get());
            }
            sleep(5);
            interval.set(currentTimeMillis);
            System.out.println(Thread.currentThread().getName() + " : " + System.currentTimeMillis() + ": i will be running.");
        }, 1, 2, TimeUnit.SECONDS);
    }


    /**
     * todo sleep the specify SECONDS
     *
     * @param SECONDS
     */
    private static void sleep(long SECONDS) {
        try {
            TimeUnit.SECONDS.sleep(SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
