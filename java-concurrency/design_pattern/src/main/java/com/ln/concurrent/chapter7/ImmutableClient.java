package com.ln.concurrent.chapter7;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter7
 * @version: 1.0
 */

import java.util.stream.IntStream;

/**
 * @ClassName:ImmutableClient
 * @Author:linianest
 * @CreateTime:2020/3/25 12:43
 * @version:1.0
 * @Description TODO:
 */
public class ImmutableClient {
    public static void main(String[] args) {
        // share data
        Person person = new Person("Alex", "HuBei");
        IntStream.range(0, 5).forEach(i -> {
            new UserPersonThread(person).start();
        });

    }
}
