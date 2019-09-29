package com.ln.ct.common.bean;

/**
 * @Description TODO 数据对象
 * @AUTHOR LiNian
 * @DATE 2019/9/22 12:55
 */
public abstract class Data implements Val{

    public String content;


    @Override
    public Object getValue() {
        return content;
    }

    @Override
    public void setValue(Object val) {
         content= (String) val;
    }
}
