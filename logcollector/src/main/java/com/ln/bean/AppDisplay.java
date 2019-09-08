package com.ln.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品点击日志
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/1 19:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppDisplay {

    private String action;
    private String goodsId;
    private String place;
    private String extend1;
    private String category;


}
