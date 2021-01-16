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
public class PhaserExample1 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        final Phaser phaser = new Phaser();
        IntStream.rangeClosed(1, 5).boxed()
                .map(i -> phaser)
                .forEach(Task::new);
        // 将main线程注册进入管理团队
        phaser.register();
        phaser.arriveAndAwaitAdvance();
        System.out.println("all of worker finished the task.");
    }

    static class Task extends Thread {

        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
            // 将自己加入到管理团队里
            this.phaser.register();
            start();
        }

        @Override
        public void run() {
            System.out.println("The worker [" + getName() + "] is working....");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 已经准备好
            phaser.arriveAndAwaitAdvance();
        }

    }
}
