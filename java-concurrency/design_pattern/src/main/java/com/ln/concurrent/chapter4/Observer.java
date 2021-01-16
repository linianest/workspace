package com.ln.concurrent.chapter4;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter4
 * @version: 1.0
 */

/**
 * 定义观察者内容与方法
 */
public abstract class Observer {

    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    abstract void update();
}
