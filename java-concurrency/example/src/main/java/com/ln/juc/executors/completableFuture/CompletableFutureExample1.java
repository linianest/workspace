package com.ln.juc.executors.completableFuture;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors.completableFuture
 * @Name:completableFutureExample1
 * @Author:linianest
 * @CreateTime:2021/1/10 22:09
 * @version:1.0
 * @Description TODO:CompletableFuture:是future与ExecutorService的结合体
 */
public class CompletableFutureExample1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

//        test();
//        test2();
        test2_1();
    }


    /**
     * CompletableFuture:默认被设置成守护线程
     *
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static void test() throws InterruptedException, ExecutionException, TimeoutException {
       /* final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executorService.submit(()->{
            sleep(10);
        });*/

        CompletableFuture.runAsync(() -> {
            sleep(3);
        }).whenComplete((r, t) -> {
            System.out.println("done");
        });

        System.out.println("====i am not blocked.======");
        Thread.currentThread().join();
    }

    /**
     * todo 从网络中获取一批网络数据，然后进行第二阶段的操作
     * 通过原始的future，两个阶段的任务是串行化执行，第二阶段的任务必须等到第一阶段的所有任务执行完成，才能执行
     * 缺点：第一阶段先执行完成的任务，只能等后面的结果出来才能执行第二阶段的任务，效率低
     */
    private static void test2() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> tasks = IntStream.range(0, 10).boxed()
                .map(i -> (Callable<Integer>) () -> get()).collect(Collectors.toList());

        executorService.invokeAll(tasks).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).parallel().forEach(CompletableFutureExample1::display);
    }

    /**
     * todo 两个阶段的任务，先执行完成第一阶段，继续执行第二阶段任务，所有的数据之间是并行化执行
     * todo 任务串行化，数据之间并行化
     * @throws InterruptedException
     */
    private static void test2_1() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        IntStream.range(0, 10).boxed()
                .forEach(i ->
                        CompletableFuture.supplyAsync(CompletableFutureExample1::get)
                                .thenAccept(CompletableFutureExample1::display)
                                .whenComplete((r, t) -> System.out.println(i + " i am done"))
                );


    }

    /**
     * 第二阶段的任务：显示数据
     *
     * @param data
     */
    private static void display(int data) {
        int value = ThreadLocalRandom.current().nextInt(20);
        System.out.println(Thread.currentThread().getName() + " display will be sleep " + value);
        sleep(value);
        System.out.println(Thread.currentThread().getName() + " display execute done. " + data);
    }

    /**
     * 从其他地方获取资源数据
     *
     * @return
     */
    private static int get() {
        int value = ThreadLocalRandom.current().nextInt(20);
        System.out.println(Thread.currentThread().getName() + " get will be sleep " + value);
        sleep(value);
        System.out.println(Thread.currentThread().getName() + " get execute done. " + value);
        return value;
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
