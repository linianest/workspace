package com.ln.ct.web.service.impl;

import com.ln.ct.web.bean.Calllog;
import com.ln.ct.web.dao.CalllogDao;
import com.ln.ct.web.service.CalllogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description 通话日志服务对象
 * @AUTHOR LiNian
 * @DATE 2019/9/29 22:24
 */
@Component
public class CalllogServiceImpl implements CalllogService {

    @Autowired
    private CalllogDao calllogDao;

    /**
     * 查询用户指定时间的通话统计信息
     * @param tel
     * @param calltime
     * @return
     */
    @Override
    public List<Calllog> queryMonthDatas(String tel, String calltime) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tel",tel);
        if (calltime.length()>4){
            calltime=calltime.substring(0,4);
        }
        paramMap.put("year",calltime);

        return calllogDao.queryMonthDatas(paramMap);
    }
}
