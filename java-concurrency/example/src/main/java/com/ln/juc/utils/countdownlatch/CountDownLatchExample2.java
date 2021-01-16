package com.ln.juc.utils.countdownlatch;

import com.ln.juc.utils.RandomUtils;
import com.ln.juc.utils.ThreadUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.utils.countdownlatch
 * @Name:CountDownLatchExample1
 * @Author:linianest
 * @CreateTime:2021/1/16 10:59
 * @version:1.0
 * @Description TODO: 使用CountDownLatch的计数器的方式统计完成任务
 */
public class CountDownLatchExample2 {

    private final static ExecutorService executorService = Executors.newFixedThreadPool(2);
    private final static CountDownLatch latch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {

        int[] data = query();

        IntStream.range(0, data.length).forEach(i -> {
                    executorService.execute(new SimpleRunnable(data, i,latch));
                }
        );

        // 等待CountDownLatch的计数器为0
        latch.await();
        System.out.println("all of the work finish done.");
        // 关闭线程池
        executorService.shutdown();
    }

    static class SimpleRunnable implements Runnable {
        private final int[] data;
        private final int index;
        private CountDownLatch latch;


        public SimpleRunnable(int[] data, int index, CountDownLatch latch) {
            this.data = data;
            this.index = index;
            this.latch = latch;
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
            // 统计完成任务，完成一个任务，减少一个计数
            latch.countDown();
        }
    }

    private static int[] query() {
//        return IntStream.rangeClosed(1, 10).toArray();
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }
}
