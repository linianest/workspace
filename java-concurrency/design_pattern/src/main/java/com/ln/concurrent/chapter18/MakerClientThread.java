package com.ln.concurrent.chapter18;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter18
 * @Name:MakerClientThread
 * @Author:linianest
 * @CreateTime:2021/1/4 16:11
 * @version:1.0
 * @Description TODO:
 */
public class MakerClientThread extends Thread {
    private final ActiveObject activeObject;
    private final char fillChar;

    public MakerClientThread(ActiveObject activeObject, String name) {
        super(name);
        this.activeObject = activeObject;
        this.fillChar = name.charAt(0);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Result result = activeObject.makeString(i + 1, fillChar);
                Thread.sleep(2_0);
                 String value = (String) result.getResultValue();
                System.out.println(Thread.currentThread().getName() + ": value=" + value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
