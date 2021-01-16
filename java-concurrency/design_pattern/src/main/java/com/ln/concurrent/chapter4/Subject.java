package com.ln.concurrent.chapter4;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter4
 * @version: 1.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:Subject
 * @Author:linianest
 * @CreateTime:2020/3/23 9:38
 * @version:1.0
 * @Description TODO: 观察者观察的主题
 */
public class Subject {
    List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if (this.state == state)
            return;

        this.state = state;
        notifyAllobserver();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllobserver() {
        observers.stream().forEach(Observer::update);
    }
}
