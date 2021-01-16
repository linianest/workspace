package com.ln.concurrency.chapter2;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2
 * @version: 1.0
 */

/**
 *@ClassName:TicketWindowRunnable
 *@Author:linianest
 *@CreateTime:2020/3/12 15:12
 *@version:1.0
 *@Description TODO:
 */
public class TicketWindowRunnable implements Runnable {
    private int index = 1;

    private final static int MAX = 50;
    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread()  + "当前的号码是：" + (index++));
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
