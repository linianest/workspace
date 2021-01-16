package com.ln.concurrency.chapter2;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2
 * @version: 1.0
 */

/**
 * @ClassName:BankVersion2
 * @Author:linianest
 * @CreateTime:2020/3/12 17:40
 * @version:1.0
 * @Description TODO:
 */
public class BankVersion2 {
    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
        Thread windowThread1 = new Thread(ticketWindowRunnable, "一号窗口");
        Thread windowThread2 = new Thread(ticketWindowRunnable, "二号窗口");
        Thread windowThread3 = new Thread(ticketWindowRunnable, "三号窗口");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
    }

}
