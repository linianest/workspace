package com.ln.juc.utils.forkjoin;


import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.utils.forkjoin
 * @Name:ForkJoinRecursiveAction
 * @Author:linianest
 * @CreateTime:2021/1/15 20:45
 * @version:1.0
 * @Description TODO:ForkJoin,无返回值的
 */
public class ForkJoinRecursiveAction {

    // 分任务的阈值
    private final static int MAX_THRESHOLD = 3;

    private final static AtomicInteger SUM = new AtomicInteger(0);


    public static void main(String[] args) throws InterruptedException {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        int result = 0;
        forkJoinPool.submit(new CalculateRecursiveAction(0, 10));
//        forkJoinPool.awaitTermination(5, TimeUnit.SECONDS);

        //阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        Optional.of(SUM).ifPresent(System.out::println);
        // 关闭线程池
        forkJoinPool.shutdown();
    }

    private static class CalculateRecursiveAction extends RecursiveAction {

        private final int start;
        private final int end;

        private CalculateRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= MAX_THRESHOLD) {
                SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
            } else {
                int middle = (start + end) / 2;
                CalculateRecursiveAction leftAction = new CalculateRecursiveAction(start, middle);
                CalculateRecursiveAction rightAction = new CalculateRecursiveAction(middle + 1, end);

                leftAction.fork();
                rightAction.fork();
            }
        }
    }
}
