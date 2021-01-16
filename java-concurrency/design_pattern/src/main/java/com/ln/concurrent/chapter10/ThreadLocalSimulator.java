package com.ln.concurrent.chapter10;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter10
 * @Name:ThreadLocalSimulator
 * @Author:linianest
 * @CreateTime:2020/3/29 14:22
 * @version:1.0
 * @Description TODO: 自定义实现ThreadLocal
 */
public class ThreadLocalSimulator<T> {

    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            storage.put(key, t);
        }
    }

    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if (value==null){
                return initialValue();
            }
            return value;
        }
    }

    public T initialValue(){
        return null;
    }


}
