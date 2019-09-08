package com.ln.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/1 19:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppLoading {
    private String action;
    private String loading_time;
    private String type1;
    private String type;
    private String loading_way;
    private String extend1;
    private String extend2;
}
