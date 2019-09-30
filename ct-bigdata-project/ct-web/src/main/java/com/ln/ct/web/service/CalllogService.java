package com.ln.ct.web.service;


import com.ln.ct.web.bean.Calllog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CalllogService {
    List<Calllog> queryMonthDatas(String tel, String calltime);
}
