package com.ln.juc.concurrent;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.concurrent
 * @Name:CopyOnWriteListExample
 * @Author:linianest
 * @CreateTime:2021/1/13 20:49
 * @version:1.0
 * @Description TODO: CopyOnWriteList是ArrayList的高并发实现，适合多读少写的场景
 */
public class CopyOnWriteArrayListExample{

    public static void main(String[] args) {
        final CopyOnWriteArrayList<Object> queue = new CopyOnWriteArrayList<>();
    }
}
