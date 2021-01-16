package com.ln.concurrent.chapter18;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter18
 * @Name:DisPlayClientThread
 * @Author:linianest
 * @CreateTime:2021/1/4 16:07
 * @version:1.0
 * @Description TODO:
 */
public class DisPlayClientThread extends Thread {
    private final ActiveObject activeObject;

    public DisPlayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                String text = Thread.currentThread().getName() + "=>" + i;
                activeObject.displayString(text);
                Thread.sleep(2_00);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
