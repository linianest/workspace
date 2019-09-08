package com.ln.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/1 19:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppNewsDetail {

    private String entry;
    private String action;
    private String goodsId;
    private String showtype;
    private String news_staytime;
    private String loading_time;
    private String type1;
    private String category;

}
