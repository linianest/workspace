package com.ln.juc.utils.countdownlatch;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.utils.countdownlatch
 * @Name:StatisticalReportExample
 * @Author:linianest
 * @CreateTime:2021/1/16 12:45
 * @version:1.0
 * @Description TODO: 统计报表给前端使用
 */

/**
 * **模拟代码**
 *
 * 1、分别统计4个指标用户新增数量、订单数量、商品的总销量、总销售额；
 *
 * 2、假设每个指标执行时间为3秒。如果是串行化的统计方式那么总执行时间会为12秒。
 *
 * 3、我们这里使用多线程并行，开启4个子线程分别进行统计
 *
 * 4、主线程等待4个子线程都执行完毕之后，返回结果给前端。
 */
public class StatisticalReportExample {
    //用于聚合所有的统计指标
    private static ConcurrentHashMap<String,Integer> map=new ConcurrentHashMap<>();
    //创建计数器，这里需要统计4个指标
    private static CountDownLatch countDownLatch=new CountDownLatch(4);

    public static void main(String[] args) {
        //记录开始时间
        long startTime=System.currentTimeMillis();

        Thread countUserThread=new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("正在统计新增用户数量");
                    Thread.sleep(3000);//任务执行需要3秒
                    map.put("userNumber",1);//保存结果值
                    countDownLatch.countDown();//标记已经完成一个任务
                    System.out.println("统计新增用户数量完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread countOrderThread=new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("正在统计订单数量");
                    Thread.sleep(3000);//任务执行需要3秒
                    map.put("countOrder",2);//保存结果值
                    countDownLatch.countDown();//标记已经完成一个任务
                    System.out.println("统计订单数量完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread countGoodsThread=new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("正在商品销量");
                    Thread.sleep(3000);//任务执行需要3秒
                    map.put("countGoods",3);//保存结果值
                    countDownLatch.countDown();//标记已经完成一个任务
                    System.out.println("统计商品销量完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread countmoneyThread=new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("正在总销售额");
                    Thread.sleep(3000);//任务执行需要3秒
                    map.put("countmoney",4);//保存结果值
                    countDownLatch.countDown();//标记已经完成一个任务
                    System.out.println("统计销售额完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        //启动子线程执行任务
        countUserThread.start();
        countGoodsThread.start();
        countOrderThread.start();
        countmoneyThread.start();

        try {
            //主线程等待所有统计指标执行完毕
            countDownLatch.await();
            long endTime=System.currentTimeMillis();//记录结束时间
            System.out.println("------统计指标全部完成--------");
            System.out.println("统计结果为："+map.toString());
            System.out.println("任务总执行时间为"+(endTime-startTime)/1000+"秒");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
