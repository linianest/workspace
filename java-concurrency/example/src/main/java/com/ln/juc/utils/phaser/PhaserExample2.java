package com.ln.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

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
public class PhaserExample2 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * 场景：运动会比赛
     * 跑步、跳高、游泳
     *
     * @param args
     */
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);
        for (int i = 1; i < 6; i++) {
            new Athletes(i, phaser).start();
        }
        // 将main线程注册进入管理团队
    }

    static class Athletes extends Thread {

        private final int no;
        private final Phaser phaser;

        Athletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {

            try {
                System.out.println(no + ": start running....");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end running....");
                System.out.println("getPhase()=>" + phaser.getPhase());
                // 已经准备好
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + ": start jumping....");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end jumping....");
                System.out.println("getPhase()=>" + phaser.getPhase());
                // 已经准备好
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + ": start swimming....");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end swimming....");
                System.out.println("getPhase()=>" + phaser.getPhase());
                // 已经准备好
                phaser.arriveAndAwaitAdvance();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
