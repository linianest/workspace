package com.ln.concurrency.chapter6;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter6
 * @version: 1.0
 */

/**
 * @ClassName:ThreadInterupt
 * @Author:linianest
 * @CreateTime:2020/3/14 19:04
 * @version:1.0
 * @Description TODO: 线程中断
 */
public class ThreadInterrupt {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
       /* Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    // 1.中断是在sleep时，捕获
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        System.out.println("收到线程打断信号.");
//                        e.printStackTrace();
//                    }

                    // 2.中断是在wait时,捕获
                    synchronized (MONITOR) {
                        try {
                            MONITOR.wait(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println(">>" + isInterrupted());
                        }
                    }
                }
            }
        };
        t.start();
        Thread.sleep(100);
        System.out.println(t.isInterrupted());
        // interrupt() 和Thread.interrupt()是一样的效果，Thread是当前线程
        t.interrupt();
        System.out.println(t.isInterrupted());*/



       Thread t = new Thread(){
           @Override
           public void run() {
               while (true){}
           }
       };
       t.start();
        Thread main = Thread.currentThread();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                main.interrupt();
                System.out.println("interrupt");
            }
        };
        t2.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
