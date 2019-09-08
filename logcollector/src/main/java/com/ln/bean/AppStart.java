package com.ln.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/1 17:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppStart extends AppBase {


    private String entry;
    private String open_ad_type;
    private String action;
    private String loading_time;
    private String detail;
    private String extend1;
    private String en;
}
