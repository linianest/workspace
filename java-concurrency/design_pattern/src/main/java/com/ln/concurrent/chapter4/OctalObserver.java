package com.ln.concurrent.chapter4;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter4
 * @version: 1.0
 */

/**
 * @ClassName:BinaryObserver
 * @Author:linianest
 * @CreateTime:2020/3/23 10:27
 * @version:1.0
 * @Description TODO: 八进制观察者
 */
public class OctalObserver extends Observer {
    public OctalObserver(Subject subject) {
        super(subject);
    }


    @Override
    public void update() {

        System.out.println("Octal String:" + Integer.toOctalString(subject.getState()));
    }
}
