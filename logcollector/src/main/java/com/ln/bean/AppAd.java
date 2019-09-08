package com.ln.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/1 19:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppAd {

    private String entry;
    private String action;
    private String content;
    private String detail;
    private String source;
    private String behavior;
    private String newstype;
    private String show_style;
}
