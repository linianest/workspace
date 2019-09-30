package com.ln.ct.web.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @AUTHOR LiNian
 * @DATE 2019/9/30 0:04
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Calllog {

    private Integer id;
    private Integer telid;
    private Integer dateid;
    private Integer sumcall;
    private Integer sumduration;

}
