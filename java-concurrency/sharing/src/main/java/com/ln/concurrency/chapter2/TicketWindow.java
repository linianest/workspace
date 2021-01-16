package com.ln.concurrency.chapter2;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2
 * @version: 1.0
 * @ClassName:TicketWindow
 * @Author:linianest
 * @CreateTime:2020/3/12 10:48
 * @version:1.0
 * @Description TODO:
 * @ClassName:TicketWindow
 * @Author:linianest
 * @CreateTime:2020/3/12 10:48
 * @version:1.0
 * @Description TODO:
 */

/**
 * @ClassName:TicketWindow
 * @Author:linianest
 * @CreateTime:2020/3/12 10:48
 * @version:1.0
 * @Description TODO:
 */

/**
 * static变量也称为静态变量，静态变量和非静态变量的区别：
 * 静态变量被所有对象共享，在内存中只有一个副本，在类初次加载的时候才会初始化
 * 非静态变量是对象所拥有的，在创建对象的时候被初始化，存在多个副本，各个对象拥有的副本互不影响
 */
public class TicketWindow extends Thread {


    private final String name;
    private static final int MAX = 50;
    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println("柜台：" + name + "当前的号码是：" + (index++));
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
