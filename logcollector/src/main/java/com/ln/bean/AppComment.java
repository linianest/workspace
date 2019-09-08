package com.ln.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @AUTHOR LiNian
 * @DATE 2019/9/1 21:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppComment {

    private int comment_id;
    private int userid;
    private int p_comment_id;
    private String content;
    private String addtime;
    private int other_id;
    private int praise_count;
    private int replay_count;
}
