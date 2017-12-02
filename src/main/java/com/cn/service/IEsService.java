package com.cn.service;

import com.cn.vo.ActionLog;
import com.cn.vo.ActionLogEx;
import com.cn.vo.JobLog;
import com.cn.vo.JobLogEx;

import java.util.Map;

public interface IEsService {
    public void addJobLog(JobLog log);

    //操作日志相关
    public void addActionLog(ActionLog log);

    public Map getActionLogPageByEntity(ActionLogEx queryLog, Integer pageNo, Integer pageSize)throws Exception;
    public Map getJobLogPageByEntity(JobLogEx queryLog, Integer pageNo, Integer pageSize)throws Exception;


}
