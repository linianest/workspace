package com.ln.concurrent.chapter4;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter4
 * @version: 1.0
 */

/**
 *@ClassName:BinaryObserver
 *@Author:linianest
 *@CreateTime:2020/3/23 10:27
 *@version:1.0
 *@Description TODO: 8进制观察者
 */
public class BinaryObserver extends Observer{
    public BinaryObserver(Subject subject){
        super(subject);
    }
    @Override
    public void update() {
        System.out.println("Binary String:"+Integer.toBinaryString(subject.getState()));
    }
}
