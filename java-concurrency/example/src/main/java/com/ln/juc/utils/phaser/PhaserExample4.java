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
public class PhaserExample4 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * 场景：运动会比赛
     * 跑步、跳高、游泳
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        test1();

        final Phaser phaser = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return true;
            }
        };
        new OnAdvanceTask("Alex", phaser).start();
        new OnAdvanceTask("Jack", phaser).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(phaser.getUnarrivedParties());
        System.out.println(phaser.getArrivedParties());

    }

    /**
     * OnAdvance:todo 判断当前phaser是否终止
     */
    static class OnAdvanceTask extends Thread {
        private final Phaser phaser;

        OnAdvanceTask(String name, Phaser phaser) {
            super(name);
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName() + " i am start and the phaser is " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            System.out.println(getName() + " i am end.");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(getName().startsWith("A")){
                System.out.println(getName() + " i am start and the phaser is " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println(getName() + " i am end.");
            }
            System.out.println(phaser.isTerminated());

        }
    }


    private static void test1() throws InterruptedException {
        final Phaser phaser = new Phaser();
        // 将main线程注册进入管理团队
        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());

        phaser.bulkRegister(10);
        System.out.println(phaser.getRegisteredParties());
        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());

        new Thread(phaser::arriveAndAwaitAdvance).start();
        TimeUnit.SECONDS.sleep(1);

        System.out.println("================================");
        System.out.println(phaser.getRegisteredParties());
        // phaser::arriveAndAwaitAdvance 会导致计数
        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());
    }

}
