package com.ln.concurrent.chapter11;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter11
 * @Name:ActionContext
 * @Author:linianest
 * @CreateTime:2020/3/30 9:18
 * @version:1.0
 * @Description TODO: 将上下文内容与ThreadLocal绑定
 */
public final class ActionContext {
    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    /**
     * 单例设计模式
     */
    private static class ContextHolder {
        private final static ActionContext actionContext = new ActionContext();
    }

    public static ActionContext getActionContext() {
        return ContextHolder.actionContext;
    }

    public Context getContext() {
        return threadLocal.get();
    }


}
