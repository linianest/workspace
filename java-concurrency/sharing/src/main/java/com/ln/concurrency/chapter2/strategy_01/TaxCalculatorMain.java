package com.ln.concurrency.chapter2.strategy_01;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter2
 * @Name:TaxCalculatorMain
 * @Author:linianest
 * @CreateTime:2020/12/26 17:51
 * @version:1.0
 * @Description TODO: 策略方式的使用
 */
public class TaxCalculatorMain {

    public static void main(String[] args) {
        // 用jdk8的lambada的方式
        TaxCalacuator calculator = new TaxCalacuator(10000d, 2000d,(salary,bonus)-> {
            return salary * 0.1 + bonus * 0.15;
        });

        // 将执行策略的具体实现放入策略中
        CalculatorStrategy strategy=new SimpleCalculatorStrategy();

        System.out.println(calculator.calcTax());
    }
}
