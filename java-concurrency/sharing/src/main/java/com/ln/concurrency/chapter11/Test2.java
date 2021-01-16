package com.ln.concurrency.chapter11;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter11
 * @version: 1.0
 */

import java.util.Arrays;
import java.util.Optional;

/**
 * @ClassName:Test2
 * @Author:linianest
 * @CreateTime:2020/3/18 18:45
 * @version:1.0
 * @Description TODO: 获取代码调用栈的信息
 */
public class Test2 {
    public void test() {
        Arrays.asList(Thread.currentThread().getStackTrace())
                .stream()
                .filter(e -> !e.isNativeMethod())
                .forEach(e -> Optional.of(e.getClassName() + ":" + e.getMethodName() + ":" + e.getLineNumber())
                        .ifPresent(System.out::println)
                );
    }
}
