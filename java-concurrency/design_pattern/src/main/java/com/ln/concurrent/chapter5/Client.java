package com.ln.concurrent.chapter5;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter5
 * @version: 1.0
 */

/**
 *@ClassName:Client
 *@Author:linianest
 *@CreateTime:2020/3/24 9:17
 *@version:1.0
 *@Description TODO: 实现一个资源每次只有一个线程访问
 */
public class Client {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User bj = new User("Baobao","Beijin",gate);
        User sh = new User("ShangLao","ShangHai",gate);
        User gz = new User("GuangLao","GuangZhou",gate);

        bj.start();
        sh.start();
        gz.start();
    }
}
