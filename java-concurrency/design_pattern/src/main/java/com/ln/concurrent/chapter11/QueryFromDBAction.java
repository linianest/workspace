package com.ln.concurrent.chapter11;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter11
 * @Name:QueryAction
 * @Author:linianest
 * @CreateTime:2020/3/29 19:13
 * @version:1.0
 * @Description TODO: 请求从DB中获取数据
 */
public class QueryFromDBAction {

    public void execute() {

        try {
            Thread.sleep(1000L);
            String name = "Alex " + Thread.currentThread().getName() ;
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
