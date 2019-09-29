package com.ln.ct.producer.bean;

import com.ln.ct.common.bean.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description TODO 联系人
 * @AUTHOR LiNian
 * @DATE 2019/9/22 14:11
 */

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends Data {

    private String tel;
    private String name;


    @Override
    public void setValue(Object val) {
        content= (String) val;
        String[] values = content.split("\t");
        setTel(values[0]);
        setName(values[1]);
    }
}
