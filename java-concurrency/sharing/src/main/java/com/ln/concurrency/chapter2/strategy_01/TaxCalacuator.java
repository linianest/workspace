package com.ln.concurrency.chapter2.strategy_01;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2
 * @Name:TaxCalacuator
 * @Author:linianest
 * @CreateTime:2020/12/26 17:47
 * @version:1.0
 * @Description TODO: 策略模式：提供税率返回
 */
public class TaxCalacuator {
    private final double salary;
    private final double bonus;

    private final CalculatorStrategy calculatorStrategy;

    public TaxCalacuator(double salary, double bonus, CalculatorStrategy calculatorStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }


    protected double calcTax() {
        return calculatorStrategy.calculate(salary, bonus);
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }
}
