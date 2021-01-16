package com.ln.concurrent.chapter7;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter7
 * @version: 1.0
 */

import java.util.Collections;
import java.util.List;

/**
 * @ClassName:ImmutableTest
 * @Author:linianest
 * @CreateTime:2020/3/25 13:23
 * @version:1.0
 * @Description TODO: 不可变对象的方法属性
 */

/**
 * 1、final修饰的成员变量只能初始化一次赋值。1：直接赋值 2：构造函数中赋值
 *   final修饰的成员变量是基本类型，不可被修改，引用类型也如此，地址不可被修改，但是引用对象里面的数据是可以被修改的
 * 2、final修饰的类，是不可被继承的，成员方法都会被隐式指定为final类型方法，一般工具类使用final关键字使用
 * 3、private方法被隐式final修饰的，如果父类的方法时final修饰的，子类不能去重写
 */
public class ImmutableTest {

    private final int age;
    private final String name;
    private final List<String> list;

    public ImmutableTest(int age, String name, List<String> list) {
        this.age = age;
        this.name = name;
        this.list = list;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    /**
     * 如果不想返回的list被别人修改，可以返回不可变的集合
     * @return
     */
    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }
}
