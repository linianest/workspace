package com.ln.concurrency.chapter10;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter10
 * @version: 1.0
 */

/**
 * @ClassName:ExitCapture
 * @Author:linianest
 * @CreateTime:2020/3/18 16:34
 * @version:1.0
 * @Description TODO: 程序异常退出，注入钩子，释放资源
 */

/**
 * 在linux状态下，kill能捕捉到，但是kill -9不行
 */
public class ExitCapture {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The Application will be exit.");
            notifyAndRelease();
        }));

        int i = 0;
        while (true) {
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if (i > 20)
                throw new RuntimeException("error");


        }
    }

    private static void notifyAndRelease() {
        System.out.println("notify to the admin.");
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("will release resource(socket,file,connect)");
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Release and Notify Done.");
    }
}
