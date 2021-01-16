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
public class SynchronizedRunnable implements Runnable {
    private int index = 1;

    // readonly shared data
    private final static int MAX = 50;

    // this
    @Override
    public void run() {

        while (true) {
            if (ticket())
                break;
        }
    }

    private synchronized boolean ticket() {
        // 1.getField
        if (index > MAX)
            return true;
        try {
            Thread.sleep(5_0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //1. index++ => index=index+1
        //2. index = index+1
        //3. put field index
        System.out.println(Thread.currentThread() + "当前的号码是：" + (index++));
        return false;
    }
}
