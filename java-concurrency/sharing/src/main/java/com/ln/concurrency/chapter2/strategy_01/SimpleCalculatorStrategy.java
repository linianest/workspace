package com.ln.concurrency.chapter2.strategy_01;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2
 * @Name:SimpleCalculatorStrategy
 * @Author:linianest
 * @CreateTime:2020/12/26 17:52
 * @version:1.0
 * @Description TODO: 实现策略定义的接口
 */
public class SimpleCalculatorStrategy implements CalculatorStrategy {
    private static final double SALARY_RATE = 0.1;
    private static final double BONUS_RATE = 0.15;

    @Override
    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }
}
