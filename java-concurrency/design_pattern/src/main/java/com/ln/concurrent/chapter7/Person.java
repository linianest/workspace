package com.ln.concurrent.chapter7;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter7
 * @version: 1.0
 * @ClassName:Person
 * @Author:linianest
 * @CreateTime:2020/3/25 12:36
 * @version:1.0
 * @Description TODO: 定义不可变对象
 * @ClassName:Person
 * @Author:linianest
 * @CreateTime:2020/3/25 12:36
 * @version:1.0
 * @Description TODO: 定义不可变对象
 */

/**
 * @ClassName:Person
 * @Author:linianest
 * @CreateTime:2020/3/25 12:36
 * @version:1.0
 * @Description TODO: 定义不可变对象
 */

/**
 * 1、变量不提供setter方法
 * 2、所有变量都是final和private
 * 3、不提供子类可以继承的方法（final修饰）
 */
public final class Person {
    private final String name;
    private final String address;

    public Person(final String name, final String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
