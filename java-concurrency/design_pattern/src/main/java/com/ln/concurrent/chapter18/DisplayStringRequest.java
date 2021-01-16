package com.ln.concurrent.chapter18;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter18
 * @Name:DisplayStringRequest
 * @Author:linianest
 * @CreateTime:2021/1/4 15:41
 * @version:1.0
 * @Description TODO:
 */
public class DisplayStringRequest extends MethodRequest {
    private final String text;

    public DisplayStringRequest(Servant servant, final String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {

        this.servant.displayString(text);
    }
}
