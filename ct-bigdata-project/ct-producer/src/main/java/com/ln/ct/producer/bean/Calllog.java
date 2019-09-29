package com.ln.ct.producer.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/22 15:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calllog {
    private String call1;
    private String call2;
    private String calltime;
    private String duration;

    @Override
    public String toString() {
        return call1+"\t"+call2+"\t"+calltime+"\t"+duration;
    }
}
