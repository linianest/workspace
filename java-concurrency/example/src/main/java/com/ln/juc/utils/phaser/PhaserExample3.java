package com.ln.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

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
public class PhaserExample3 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * 场景：运动会比赛
     * 跑步、跳高、游泳
     *
     * @param args
     */
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);
        for (int i = 1; i < 5; i++) {
            new Athletes(i, phaser).start();
        }
        new InjuredAthletes(5, phaser).start();
        // 将main线程注册进入管理团队
    }

    static class InjuredAthletes extends Thread {

        private final int no;
        private final Phaser phaser;

        InjuredAthletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {

            try {
                sport(no, phaser, ": start running....", ": end running....");

                sport(no, phaser, ": start jumping....", ": end jumping....");

//                System.out.println("Oh shit,i am injured.");
                System.out.println("Oh shit,i am injured, i will be exited.");
                phaser.arriveAndDeregister(); // 动态的放弃任务

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
                sport(no, phaser, ": start running....", ": end running....");

                sport(no, phaser, ": start jumping....", ": end jumping....");

                sport(no, phaser, ": start swimming....", ": end swimming....");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private static void sport(int no, Phaser phaser, String s, String s2) throws InterruptedException {
        System.out.println(no + s);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.println(no + s2);
//        System.out.println("getPhase()=>" + phaser.getPhase());
        // 已经准备好
        phaser.arriveAndAwaitAdvance();
    }
}
