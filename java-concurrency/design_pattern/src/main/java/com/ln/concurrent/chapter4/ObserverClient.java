package com.ln.concurrent.chapter4;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter4
 * @version: 1.0
 */

/**
 * @ClassName:psvm
 * @Author:linianest
 * @CreateTime:2020/3/23 11:29
 * @version:1.0
 * @Description TODO: 观察者模式客户端
 */
public class ObserverClient {

    public static void main(String[] args) {
        final Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);
        System.out.println("======================");
        subject.setState(10);
        System.out.println("======================");
        subject.setState(10);
        System.out.println("======================");
        subject.setState(15);
    }

}
