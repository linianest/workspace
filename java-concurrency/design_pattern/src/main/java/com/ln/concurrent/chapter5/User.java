package com.ln.concurrent.chapter5;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter5
 * @version: 1.0
 */

/**
 * @ClassName:User
 * @Author:linianest
 * @CreateTime:2020/3/24 9:11
 * @version:1.0
 * @Description TODO: 定义调用资源的线程
 */
public class User extends Thread {

    private final String myName;
    private final String myAdress;
    private final Gate gate;
    private static volatile int number;

    public User(String myName, String myAdress, Gate gate) {
        this.myName = myName;
        this.myAdress = myAdress;
        this.gate = gate;
    }


    @Override
    public void run() {
        System.out.println(myName + " BEGIN");
        while (true) {
            this.gate.pass(myName, myAdress);
            try {
                Thread.sleep(1_000L);
                System.out.println("the thread " + Thread.currentThread().getName() + " coming ,the number is " + (number++));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
