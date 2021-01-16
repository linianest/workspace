package com.ln.concurrency.chapter5;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter5
 * @version: 1.0
 */

/**
 * @ClassName:ThreadJoin3
 * @Author:linianest
 * @CreateTime:2020/3/14 18:08
 * @version:1.0
 * @Description TODO: 模拟多线程采集多台机器信息,信息入库,结束时间以最后的线程执行完为准.
 */
public class ThreadJoin3 {

    public static void main(String[] args) throws InterruptedException {
        long startTimestamp = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunnable("M1", 10_000L));
        Thread t2 = new Thread(new CaptureRunnable("M2", 30_000L));
        Thread t3 = new Thread(new CaptureRunnable("M3", 15_000L));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long endTimestamp = System.currentTimeMillis();
        System.out.printf("Save data begin timestamp is:%s, end timestamp is:%s\n", startTimestamp, endTimestamp);
    }


}

class CaptureRunnable implements Runnable {

    private String machineName;

    private long spendTime;

    public CaptureRunnable(String machineName, long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        // do the really capture data
        try {
            Thread.sleep(spendTime);
            System.out.printf(machineName + " completed data capture at timestamp [%s] and successfully.\n", System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return machineName + " finish.";
    }
}