package com.ln.concurrent.chapter11;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter11
 * @Name:QueryFromHttpAction
 * @Author:linianest
 * @CreateTime:2020/3/29 19:23
 * @version:1.0
 * @Description TODO: 从网络获取数据
 */
public class QueryFromHttpAction {
    public void execute() {

        Context context = ActionContext.getActionContext().getContext();
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    private String getCardId(String name) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "123435344354" + Thread.currentThread().getId();
    }
}
