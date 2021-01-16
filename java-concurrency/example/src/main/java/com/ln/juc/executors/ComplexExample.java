package com.ln.juc.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:ComplexExample
 * @Author:linianest
 * @CreateTime:2021/1/10 18:18
 * @version:1.0
 * @Description TODO:Executors的陷阱：执行一组任务，按照任务执行完成先后，返回结果
 */
public class ComplexExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*test1();
        test2();
        test3();*/
        test4();

    }

    /**
     * todo 自定义callable
     */
    private static class MyTask implements Callable<Integer> {

        private final int value;


        private boolean success = false;

        MyTask(int value) {
            this.value = value;
        }

        @Override
        public Integer call() throws Exception {
            try {
                System.out.printf("The task [%d] will be executed.\n", value);
                TimeUnit.SECONDS.sleep(value * 5 + 10);
                System.out.printf("The task [%d] execute done.\n", value);
                success = true;
            } catch (InterruptedException e) {
                System.out.printf("The task [%d] be interrupted.\n", value);
            }
            return value;
        }

        private boolean isSuccess() {
            return success;
        }
    }

    /**
     * todo 找出执行失败的任务
     * todo 自定义Mytask解决completionService:关注的是那些任务执行完成，service的shutdownNow关注的是那些任务未执行，中间缺失正在执行却被中断的任务
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void test4() throws ExecutionException, InterruptedException {
        final ExecutorService service = Executors.newFixedThreadPool(1);

        final List<Callable<Integer>> tasks = IntStream.range(0, 5).boxed().map(MyTask::new).collect(Collectors.toList());

        final ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(service);

        // todo 包装任务队列
        tasks.forEach(completionService::submit);

        TimeUnit.SECONDS.sleep(12);
        service.shutdownNow();

        // todo 过滤出执行失败的任务
        tasks.stream().filter(callable -> !((MyTask) callable).isSuccess()).forEach(System.out::println);
    }

    /**
     * todo completionService:关注的是那些任务执行完成，service的shutdownNow关注的是那些任务未执行，中间缺失正在执行却被中断的任务
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void test3() throws ExecutionException, InterruptedException {
        final ExecutorService service = Executors.newFixedThreadPool(1);

        final List<Runnable> tasks = IntStream.range(0, 5).boxed().map(ComplexExample::toTask).collect(Collectors.toList());

        final ExecutorCompletionService<Object> completionService = new ExecutorCompletionService<Object>(service);

        // todo 包装任务队列
        tasks.forEach(r -> completionService.submit(Executors.callable(r)));

        TimeUnit.SECONDS.sleep(20);
        final List<Runnable> runnables = service.shutdownNow();
        System.out.println(runnables.size());
        // todo 返回的是FutureQueue中futureTask
        System.out.println(runnables);
    }

    /**
     * todo ExecutorCompletionService封装，得到执行结果封装在队列中
     * todo completionService:关注的是那些任务执行完成，service的shutdownNow关注的是那些任务未执行，中间缺失正在执行却被中断的任务
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void test2() throws ExecutionException, InterruptedException {
        final ExecutorService service = Executors.newFixedThreadPool(1);

        final List<Runnable> tasks = IntStream.range(0, 5).boxed().map(ComplexExample::toTask).collect(Collectors.toList());

        final ExecutorCompletionService<Object> completionService = new ExecutorCompletionService<Object>(service);

        // todo 包装任务队列
        tasks.forEach(r -> completionService.submit(Executors.callable(r)));

        Future<Object> future;
        // 从结果队列中获取结果
        while ((future = completionService.take()) != null) {
            //todo 由于runnable没有返回值，所以这里为null
            System.out.println(future.get());
        }
    }

    /**
     * todo 由于长时间的任务阻塞，导致先完成的任务结果无法获取
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void test1() throws ExecutionException, InterruptedException {
        final ExecutorService service = Executors.newFixedThreadPool(1);

        final List<Runnable> tasks = IntStream.range(0, 5).boxed().map(ComplexExample::toTask).collect(Collectors.toList());

        List<Future<?>> futureList = new ArrayList<>();
        tasks.forEach(r -> futureList.add(service.submit(r)));
        for (int i = 4; i > 0; i--) {
            futureList.get(i).get();
            System.out.println("=====" + i + "=======");
        }
    }

    private static Runnable toTask(int i) {
        return () -> {
            try {
                System.out.printf("The task [%d] will be executed.\n", i);
                TimeUnit.SECONDS.sleep(i * 5 + 10);
                System.out.printf("The task [%d] execute done.\n", i);
            } catch (InterruptedException e) {
                System.out.printf("The task [%d] be interrupted.\n", i);
            }
        };
    }
}
