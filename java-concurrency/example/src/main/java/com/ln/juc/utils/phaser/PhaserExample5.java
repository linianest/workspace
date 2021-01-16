package com.ln.juc.utils.phaser;

import com.ln.juc.utils.ThreadUtils;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.utils.phaser
 * @Name:PhaserExample1
 * @Author:linianest
 * @CreateTime:2021/1/15 14:47
 * @version:1.0
 * @Description TODO: Phaser 线程间同步完成各阶段任务
 */

/**
 *  Phaser 是JDK1.7版本中新增的，是一个可重用的同步barrier，它的功能与 CountDownLatch、CyclicBarrier 相似，但是使用起来更加灵活。可以用来解决控制多个线程分阶段共同完成任务的情景问题。
 */
public class PhaserExample5 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * todo 共有的第一阶段任务完成后,继续做其他的任务
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
/*

        final Phaser phaser = new Phaser(2);
        new Thread(phaser::arrive).start();

        System.out.println(phaser.arriveAndAwaitAdvance());
        TimeUnit.SECONDS.sleep(4);
*/
        final Phaser phaser = new Phaser(5);
        for (int i = 0; i < 4; i++) {
            new ArriveTask(phaser,i).start();
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println("the phase 1 i work finished done.");

    }

    private static class ArriveTask extends Thread {

        private final Phaser phaser;

        private ArriveTask(Phaser phaser, int i) {
            super(String.valueOf(i));
            this.phaser = phaser;
        }

        @Override
        public void run() {

            System.out.println(getName() + " start working....");
            ThreadUtils.sleep(random.nextInt(5));
            System.out.println(getName() + " the phaser one is running...");
            phaser.arrive();

            ThreadUtils.sleep(random.nextInt(5));
            System.out.println(getName() + " keep to do other things...");
        }
    }


}
