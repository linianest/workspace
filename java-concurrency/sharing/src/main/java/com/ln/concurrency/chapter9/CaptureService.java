package com.ln.concurrency.chapter9;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter9
 * @version: 1.0
 */

import java.util.*;
import java.util.stream.IntStream;

/**
 * @ClassName:CaptureService
 * @Author:linianest
 * @CreateTime:2020/3/16 17:35
 * @version:1.0
 * @Description TODO: 多线程采集多台主机信息任务,保持定量的线程运行.
 */
public class CaptureService {

    // 定义一个链表
    private final static LinkedList<Control> CONTROLS = new LinkedList<>();

    private final static int MAX_WORKER = 5;

    public static void main(String[] args) {
        List<Thread> worker = new ArrayList<>();
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10")
                .stream()
                .map(CaptureService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                    worker.add(t);
                });

        worker.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Optional.of("All of capture work finished").ifPresent(System.out::println);


    }

    private static Thread createCaptureThread(String name) {
        return new Thread(() -> {
            /**
             * 1、任务开始启动，启动指定数量的线程进入工作栈，其他线程进入wait状态
             */
            Optional.of("The worker [" + Thread.currentThread().getName() + "] BEGIN capture data.")
                    .ifPresent(System.out::println);
            synchronized (CONTROLS) {
                while (CONTROLS.size() > MAX_WORKER) {
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 将任务添加进工作站
                CONTROLS.addLast(new Control());
            }

            /**
             * 2、线程开始工作
             */
            Optional.of("The worker [" + Thread.currentThread().getName() + "] is working...")
                    .ifPresent(System.out::println);
            try {
                // 线程工作
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**
             * 3、工作栈中的线程完成任务,将工作栈中的线程移除工作栈
             * 并唤醒其他等待的线程
             */
            synchronized (CONTROLS) {
                Optional.of("The worker [" + Thread.currentThread().getName() + "] END capture data.")
                        .ifPresent(System.out::println);
                // 将完成任务的线程移除工作站
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        }, name);
    }

    private static class Control {


    }
}
