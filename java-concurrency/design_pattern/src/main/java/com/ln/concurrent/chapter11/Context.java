package com.ln.concurrent.chapter11;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter11
 * @Name:Context
 * @Author:linianest
 * @CreateTime:2020/3/29 19:15
 * @version:1.0
 * @Description TODO: 定义上下文
 */

/**
 * context封装的属性
 */
public class Context {

    private String name;
    private String cardId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }
}
