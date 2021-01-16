package com.ln.concurrent.chapter7;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter7
 * @version: 1.0
 */

/**
 * @ClassName:UserPersonThread
 * @Author:linianest
 * @CreateTime:2020/3/25 12:41
 * @version:1.0
 * @Description TODO:
 */
public class UserPersonThread extends Thread {

    private Person person;

    public UserPersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " print " + person.toString());
        }
    }
}
