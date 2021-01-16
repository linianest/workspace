package com.ln.juc.utils.countdownlatch;

import com.ln.juc.utils.RandomUtils;
import com.ln.juc.utils.ThreadUtils;
import com.ln.juc.utils.Tools;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.utils.countdownlatch
 * @Name:CountDownLatchExample1
 * @Author:linianest
 * @CreateTime:2021/1/16 10:59
 * @version:1.0
 * @Description TODO: 使用线程池的awaitTermination方法同步完成任务
 */
public class CountDownLatchExample1 {

    private final static ExecutorService executorService = Executors.newFixedThreadPool(2);


    public static void main(String[] args) throws InterruptedException {

        int[] data = query();
//        System.out.println(Arrays.toString(data));

        IntStream.range(0, data.length).forEach(i -> {
                    executorService.execute(new SimpleRunnable(data, i));
                }
        );
//        RandomUtils.getRandom().

        // 关闭线程池
        executorService.shutdown();
        // 阻塞，等待所有的线程执行完任务
        executorService.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("all of the work finish done.");
    }

    static class SimpleRunnable implements Runnable {
        private final int[] data;
        private final int index;

        public SimpleRunnable(int[] data, int index) {
            this.data = data;
            this.index = index;
        }

        @Override
        public void run() {
            ThreadUtils.sleepMILLISECONDS(RandomUtils.getRandom().nextInt(2000));

            int value = data[index];
            if (value % 2 == 0) {
                data[index] = value * 2;
            } else {
                data[index] = value * 10;
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
        }
    }

    private static int[] query() {
//        return IntStream.rangeClosed(1, 10).toArray();
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }
}
