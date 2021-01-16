package com.ln.concurrency.chapter2;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2
 * @version: 1.0
 */

/**
 * @ClassName:Bank
 * @Author:linianest
 * @CreateTime:2020/3/12 10:48
 * @version:1.0
 * @Description TODO: 模拟银行多线程叫号机制
 */
public class Bank {

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一号柜台");
        ticketWindow1.start();
        TicketWindow ticketWindow2 = new TicketWindow("二号柜台");
        ticketWindow2.start();
        TicketWindow ticketWindow3 = new TicketWindow("三号柜台");
        ticketWindow3.start();
    }
}
