package com.ln.concurrency.chapter9;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter9
 * @version: 1.0
 * @ClassName:ProduceConsumerVersion2
 * @Author:linianest
 * @CreateTime:2020/3/16 12:02
 * @version:1.0
 * @Description TODO:
 * @ClassName:ProduceConsumerVersion2
 * @Author:linianest
 * @CreateTime:2020/3/16 12:02
 * @version:1.0
 * @Description TODO:
 * @ClassName:ProduceConsumerVersion2
 * @Author:linianest
 * @CreateTime:2020/3/16 12:02
 * @version:1.0
 * @Description TODO:
 * @ClassName:ProduceConsumerVersion2
 * @Author:linianest
 * @CreateTime:2020/3/16 12:02
 * @version:1.0
 * @Description TODO:
 * @ClassName:ProduceConsumerVersion2
 * @Author:linianest
 * @CreateTime:2020/3/16 12:02
 * @version:1.0
 * @Description TODO:
 */

/**
 *@ClassName:ProduceConsumerVersion2
 *@Author:linianest
 *@CreateTime:2020/3/16 12:02
 *@version:1.0
 *@Description TODO: 单个线程实现生产者和消费者
 */

/**
 * volatile:每次的读写都表示不同的含义.要考虑数据的完整性.
 * 1.中断服务程序中,修改的供其他程序检测的变量需要加volatile
 * 2.多任务环境下，各任务间共享的标志应该加volatile
 * 3.存储器映射的硬件寄存器应该加volatile说明,
 */
public class  ProduceConsumerVersion2 {

    private int i = 0;

    final private Object LOCK = new Object();

    // 生产者是否已经生产
    private volatile boolean isProduced = false;

    /**
     * 实现：如果生产者已经生产,生产者进入等待状态,
     * 否则,进行生产,生产完后,通知消费者,已经生产了数据,标志位并设置成true
     */
    public void produce() {
        synchronized (LOCK) {
            // 如果生产者已经生产
            if (isProduced) {
                try {
                    // 生产者线程处于等待状态
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // 如果没有生产，进行生产
                i++;
                System.out.println("P->" + i);
                // 生产完,通知消费者消费
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    /**
     * 消费者实现：如果生产者没有生产数据,消费者线程进入等待状态,
     * 否则,消费者进行消费数据,消费完成后,并通知生产者生产,将标志位设置成false
     */
    public void consumer() {
        synchronized (LOCK) {
            // 如果生产者已经生产
            if (isProduced) {
                // 开始消费
                System.out.println("C->" + i);
                // 消费完后,激活生产者线程进行生产,并将标志位设置成false
                LOCK.notify();
                isProduced = false;
            } else {
                // 如果没有生产
                try {
                    // 消费者进行线程等待
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();
        new Thread("P") {
            @Override
            public void run() {
                while (true)
                    pc.produce();
            }
        }.start();
        new Thread("C") {
            @Override
            public void run() {
                while (true)
                    pc.consumer();
            }
        }.start();
    }

}
