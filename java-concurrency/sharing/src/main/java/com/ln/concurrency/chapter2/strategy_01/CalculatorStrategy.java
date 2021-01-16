package com.ln.concurrency.chapter2.strategy_01;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2
 * @Name:CalculatorStrategy
 * @Author:linianest
 * @CreateTime:2020/12/26 17:45
 * @version:1.0
 * @Description TODO: 策略模式在线程中的运用
 */

/**
 * 场景：工资税率变化
 */
@FunctionalInterface
public interface CalculatorStrategy {

    double calculate(double salary,double bonus);
}
