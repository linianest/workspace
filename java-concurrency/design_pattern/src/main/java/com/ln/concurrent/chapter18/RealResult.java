package com.ln.concurrent.chapter18;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter18
 * @Name:RealResult
 * @Author:linianest
 * @CreateTime:2021/1/4 15:24
 * @version:1.0
 * @Description TODO: 真正的结果
 */
public class RealResult implements Result{
    private final Object resultValue;

    public RealResult(Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public Object getResultValue() {
        return resultValue;
    }
}
