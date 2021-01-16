package com.ln.concurrent.chapter8;


/**
 * @ClassName:Synclovker
 * @Author:linianest
 * @CreateTime:2020/3/25 14:44
 * @version:1.0
 * @Description TODO: 同步（阻塞）调用
 */

/**
 * Future        -> 代表的是未来的一个凭据
 * FutureTask    -> 将你的调用逻辑进行了隔离
 * FutureService ->桥接Future和FutureTask
 */
public class Synclnvoker {
    public static void main(String[] args) throws InterruptedException {
//        String result = get();
//        System.out.println(result);

        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        }, System.out::println);
        System.out.println("================");
        System.out.println("do other thing.");
        Thread.sleep(1_000L);
        System.out.println("================");

//        System.out.println(future.get());
    }

    public static String get() throws InterruptedException {
        Thread.sleep(10_000L);
        return "FINISH";
    }

}
