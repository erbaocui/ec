package com.cn.controller;

import com.cn.anno.Config;
import com.cn.service.IEsService;
import com.cn.util.DateUtil;
import com.cn.util.IdGenerator;
import com.cn.util.StringUtil;
import com.cn.vo.ActionLogEx;
import com.cn.vo.JobLog;
import com.cn.vo.JobLogEx;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/joblog")
@Scope("prototype")
public class JobLogController extends BaseController{

    Logger logger= Logger.getLogger(JobLogController.class.getName());


    @Autowired
    private IEsService esService;



    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }


    /**
     * 用户管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/page")
    @Config(needlogin = false,interfaceLog =false)
    public String user() throws Exception{
        return "redirect:/page/joblog/list.jsp";
    }

    /**
     * 用户列表查询
     * @param
     *
     * @return  json
     */
    @RequestMapping(value = "/list")
    @Config(needlogin = false,interfaceLog =false)
    public @ResponseBody
    Map list(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String loginName,String beginTime,String endTime,String token,Integer status,String actionUrl)throws Exception
    {

        JobLogEx queryLog=new JobLogEx ();
        Integer pageNumber= Integer.valueOf(page)-1;
        Integer pageSize= Integer.valueOf(rows);
        if(StringUtil.isNotEmpty( beginTime)){
            queryLog.setBeginTime(DateUtil.convert2Date(beginTime,"yyyy-MM-dd HH:mm:ss"));
        }
        if(StringUtil.isNotEmpty( endTime)){
            queryLog.setEndTime(DateUtil.convert2Date(endTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if(StringUtil.isNotEmpty( status)){
            if(!status.equals("-1")) {
                queryLog.setStatus(status);
            }
        }

        Map map= esService.getJobLogPageByEntity(queryLog,pageNumber,pageSize);
        Map result=new HashMap();
        result.put("total", map.get("total"));
        result.put("rows",map.get("list"));
        return result;
    }

    public void test(){

    }


    public IEsService getEsService() {
        return esService;
    }

    public void setEsService(IEsService esService) {
        this.esService = esService;
    }
}
