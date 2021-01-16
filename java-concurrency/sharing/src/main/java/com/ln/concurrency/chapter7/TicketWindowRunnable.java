package com.ln.concurrency.chapter7;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2
 * @version: 1.0
 */

/**
 * @ClassName:TicketWindowRunnable
 * @Author:linianest
 * @CreateTime:2020/3/12 15:12
 * @version:1.0
 * @Description TODO:
 */
public class TicketWindowRunnable implements Runnable {
    private int index = 1;
    private final Object MONITOR = new Object();

    private final static int MAX = 50;

    @Override
    public void run() {

        while (true) {
            synchronized (MONITOR) {
                if (index > MAX)
                    break;
                try {
                    Thread.sleep(5_0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "当前的号码是：" + (index++));
            }
        }
    }
}
