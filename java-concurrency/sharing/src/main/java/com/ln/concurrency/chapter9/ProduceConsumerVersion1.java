package com.ln.concurrency.chapter9;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter9
 * @version: 1.0
 * @ClassName:ProduceConsumerVersion1
 * @Author:linianest
 * @CreateTime:2020/3/16 11:46
 * @version:1.0
 * @Description TODO: 线程间通讯，生产者和消费者
 */

/**
 * @ClassName:ProduceConsumerVersion1
 * @Author:linianest
 * @CreateTime:2020/3/16 11:46
 * @version:1.0
 * @Description TODO: 线程间通讯，生产者和消费者
 */

/**
 * 结果：未实现
 */
public class ProduceConsumerVersion1 {

    private int i = 1;

    final private Object LOCK = new Object();

    private void produce() {
        synchronized (LOCK) {
            System.out.println("P->" + (i++));
        }
    }

    private void consumer() {
        synchronized (LOCK) {
            System.out.println("C->" + i);
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion1 pc = new ProduceConsumerVersion1();
        new Thread("P") {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                }

            }
        }.start();
        new Thread("C") {
            @Override
            public void run() {
                while (true) {
                    pc.consumer();
                }

            }
        }.start();
    }
}
