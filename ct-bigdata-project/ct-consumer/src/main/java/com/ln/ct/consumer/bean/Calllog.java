package com.ln.ct.consumer.bean;

import com.ln.ct.common.api.Column;
import com.ln.ct.common.api.Rowkey;
import com.ln.ct.common.api.TableRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO 通话日志
 * @AUTHOR LiNian
 * @DATE 2019/9/26 19:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableRef("ct:calllog")
public class Calllog {

    @Rowkey
    private String rowkey;
    @Column(family = "caller")
    private String call1;
    @Column(family = "caller")
    private String call2;
    @Column(family = "caller")
    private String calltime;
    @Column(family = "caller")
    private String duration;
    @Column(family = "caller")
    private String flag="1";

    public Calllog(String data){
        String[] values=data.split("\t");
        call1=values[0];
        call2=values[1];
        calltime=values[2];
        duration=values[3];
    }
}
