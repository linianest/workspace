package com.ln.juc.executors;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:ExecutorServiceExample1
 * @Author:linianest
 * @CreateTime:2021/1/9 11:35
 * @version:1.0
 * @Description TODO:ExecutorService API使用详解
 */

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * isShutDown：异步停止线程池：先清空休眠的线程，等待现有的线程执行任务完成后，线程池停止
 */
public class ExecutorServiceExample1 {
    public static void main(String[] args) throws InterruptedException {

//        isShutDown();
//        isTerminated();
//        executeRunnableError();
        executeRunnableTask();
    }

    /**
     * 判断是线程是否停止：线程池执行shutdown后，不能接受新的任务提交
     */
    private static void isShutDown() {

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(executorService.isShutdown());
        // 异步停止线程池：先清空休眠的线程，等待现有的线程执行任务完成后，停止
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        executorService.execute(() -> System.out.println(" i will be executed after shutdown."));
    }

    /**
     * isShutdown:线程池是否发不了关闭命令
     * isTerminated：线程池是否已经是关闭状态
     */
    private static void isTerminated() {

        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 异步执行关闭线程池的命令
        executorService.shutdown();
        // 线程池是否已经发布了关闭命令
        System.out.println(executorService.isShutdown());
        // 线程池是否已经停止
        System.out.println(executorService.isTerminated());
        // 线程池是否正在终结中
        System.out.println(((ThreadPoolExecutor) executorService).isTerminating());
    }

    /**
     * todo 线程池执行任务，任务出现异常：setUncaughtExceptionHandler
     */
    private static void executeRunnableError() throws InterruptedException {

        final ExecutorService executorService = Executors.newFixedThreadPool(10, new MyThreadFactory());
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(() -> System.out.println(i / 0)));
        executorService.shutdown();
        // 等待任务线程池执行完成后，执行后面的代码，如果任务先执行完成，也可以继续执行后面的
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("============================");
    }


    /**
     * 自定义执行任务task,处理异常
     *
     * @throws InterruptedException
     */
    private static void executeRunnableTask() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(
                new MyTask(i) {
            @Override
            protected void doError(Throwable cause) {
                System.out.println("The thread no:" + i + " failed, update status to ERROR.");
            }

            @Override
            protected void done() {
                System.out.println("The thread no:" + i + " successfully, update status to DONE.");
            }

            @Override
            protected void doExecute() {
                if (i % 3 == 0) {
                    int tmp = i % 0;
                }

            }

            @Override
            protected void doInit() {

                // do nothing
            }
        }));
        executorService.shutdown();
        // 等待任务线程池执行完成后，执行后面的代码，如果任务先执行完成，也可以继续执行后面的
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("============================");
    }

    /**
     * 自定义Runnable,能处理多重复杂状况的任务
     */
    private static abstract class MyTask implements Runnable {

        protected final int no;

        private MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {

                this.doInit();
                this.doExecute();
                this.done();
            } catch (Throwable cause) {
                this.doError(cause);
            }

        }

        // 执行出现异常
        protected abstract void doError(Throwable cause);


        // 执行成功
        protected abstract void done();

        // 执行任务
        protected abstract void doExecute();

        // 初始化
        protected abstract void doInit();
    }

    /**
     * 线程工厂:UncaughtExceptionHandler捕捉异常
     */
    private static class MyThreadFactory implements ThreadFactory {

        private final static AtomicInteger SEQ = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            final Thread thread = new Thread(r);
            thread.setName("My-Thread-" + SEQ.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println("The Thread " + t.getName() + "executed faild.");
                cause.printStackTrace();
                System.out.println("===========================");
            });
            return thread;
        }
    }
}
