package com.ln.concurrent.chapter11;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter11
 * @Name:ExecutionTask
 * @Author:linianest
 * @CreateTime:2020/3/29 19:12
 * @version:1.0
 * @Description TODO: 请求任务执行线程
 */
public class ExecutionTask implements Runnable {

    private QueryFromDBAction queryAction = new QueryFromDBAction();
    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    @Override
    public void run() {

        queryAction.execute();
        System.out.println("The name query successful.");
        httpAction.execute();
        System.out.println("The card id query successful.");

        Context context = ActionContext.getActionContext().getContext();
        System.out.println("The Name is " + context.getName() + " and CardId " + context.getCardId());
    }
}
