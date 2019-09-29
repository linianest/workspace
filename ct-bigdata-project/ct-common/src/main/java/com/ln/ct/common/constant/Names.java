package com.ln.ct.common.constant;

import com.ln.ct.common.bean.Val;

/**
 * @Description TODO 名称常量枚举类
 * @AUTHOR LiNian
 * @DATE 2019/9/22 12:58
 */
public enum Names implements Val {

    NAMESPACE("ct")
    ,TABLE("ct:calllog")
    ,CF_CALLER("caller")
    ,CF_CALLEE("callee")
    ,CF_INFO("info")
    ,TOPIC("ct");

    private String name;

    private Names(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return name;
    }

    @Override
    public void setValue(Object val) {
        this.name = (String) val;
    }
}
