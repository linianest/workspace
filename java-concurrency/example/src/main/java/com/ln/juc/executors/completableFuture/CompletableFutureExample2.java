package com.ln.juc.executors.completableFuture;

import com.ln.juc.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors.completableFuture
 * @Name:CompletableFutureExample2
 * @Author:linianest
 * @CreateTime:2021/1/10 23:02
 * @version:1.0
 * @Description TODO:CompletableFuture API详解
 */
public class CompletableFutureExample2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        supplyAsync();
//        final Object v = runAsync().get();
  /*      final Future<Void> future = completed("hello");
        System.out.println(future.isDone());*/

//        final Future anyof = anyof();
//        System.out.println(anyof.get());
        allof();
        Thread.currentThread().join();
    }

    private static void create() {
        CompletableFuture<Object> future = new CompletableFuture<>();
        Object s = null;
        CompletableFuture.supplyAsync(() -> s);
    }

    /**
     * todo 所有的任务都执行，但是没有返回值
     */
    private static void allof() {
        CompletableFuture.allOf(CompletableFuture.runAsync(() -> {
                    System.out.println("1===Start");
                    ThreadUtils.sleep(5);
                    System.out.println("1===end");
                }).whenComplete((v, t) -> System.out.println("=====over======")),
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("2===Start");
                    ThreadUtils.sleep(5);
                    System.out.println("2===end");
                    return "hello";
                }).whenComplete((v, t) -> System.out.println(v + "=====over======")));

    }

    /**
     * todo 多个任务执行，只返回任意一个任务的结果
     */
    private static Future anyof() {
        return CompletableFuture.anyOf(CompletableFuture.runAsync(() -> {
                    System.out.println("1===Start");
                    ThreadUtils.sleep(5);
                    System.out.println("1===end");
                }).whenComplete((v, t) -> System.out.println("=====over======")),
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("2===Start");
                    ThreadUtils.sleep(5);
                    System.out.println("2===end");
                    return "hello";
                }).whenComplete((v, t) -> System.out.println(v + "=====over======")));


    }

    /**
     * completed:执行完成的数据，当做数据流执行新的操作
     *
     * @param data
     * @return
     */
    private static Future<Void> completed(String data) {
        return CompletableFuture.completedFuture(data)
                .thenAccept(System.out::println);
    }

    /**
     * supply:
     * void testSupplier1() {
     * Supplier<String> supplier = () -> "这是你要的字符串";
     * String str = supplier.get();
     * System.out.println("str = " + str);
     * }
     */
    /**
     * <pre>
     *      post->{
     *          basic
     *          details
     *      }
     *      insert basic
     *      insert details
     *               insert basic
     *      [submit]              =====> action
     *               insert details
     * </pre>
     * execute result:
     * <p>
     * Obj===Start
     * String===Start
     * Obj===java.lang.Object@3dae2bb8
     * String===hello
     * ====finished.=====
     * </p>
     */
    // todo 异步返回一个结果
    private static void supplyAsync() {
        CompletableFuture.supplyAsync(Object::new)
                .thenAccept(obj -> {
                    System.out.println("Obj===Start");
                    ThreadUtils.sleep(5);
                    System.out.println("Obj===" + obj);
                }).runAfterBoth(CompletableFuture.supplyAsync(() -> "hello")
                .thenAccept(s -> {
                    System.out.println("String===Start");
                    ThreadUtils.sleep(5);
                    System.out.println("String===" + s);
                }), () -> System.out.println("====finished.====="));

    }

    private static Future<?> runAsync() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Obj===Start");
            ThreadUtils.sleep(5);
            System.out.println("Obj===end");
        }).whenComplete((v, t) -> System.out.println("=====over======"));
    }

}
