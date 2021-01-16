package com.ln.concurrent.chapter18;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter18
 * @Name:MakeStringRequest
 * @Author:linianest
 * @CreateTime:2021/1/4 15:34
 * @version:1.0
 * @Description TODO:负责makeString 方法
 */

/**
 * 负责makeString 方法
 */
public class MakeStringRequest extends MethodRequest {
    private final int count;
    private final char fillChar;

    public MakeStringRequest(Servant servant, FutureResult futureResult, int count, char fillChar) {
        super(servant, futureResult);
        this.count = count;
        this.fillChar = fillChar;
    }

    @Override
    public void execute() {

        Result result = servant.makeString(count, fillChar);
        futureResult.setResult(result);
    }
}
