package com.ln.concurrency.chapter7;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter7
 * @version: 1.0
 */

/**
 * @ClassName:BankVersion2
 * @Author:linianest
 * @CreateTime:2020/3/15 11:28
 * @version:1.0
 * @Description TODO:
 */
public class BankVersion3 {

    public static void main(String[] args) {
        final SynchronizedRunnable ticketWindow = new SynchronizedRunnable();

        Thread windowThread1 = new Thread(ticketWindow, "一号窗口");
        Thread windowThread2 = new Thread(ticketWindow, "二号窗口");
        Thread windowThread3 = new Thread(ticketWindow, "三号窗口");

        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
    }
}
