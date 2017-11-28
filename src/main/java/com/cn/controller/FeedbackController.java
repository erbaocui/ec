package com.cn.controller;

import com.cn.model.Feedback;
import com.cn.service.IFeedbackService;
import com.cn.util.DateUtil;
import com.cn.util.MD5Util;
import com.cn.util.StringUtil;
import com.cn.vo.FeedbackEx;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/feedback")
@Scope("prototype")
public class FeedbackController extends BaseController{

    Logger logger= Logger.getLogger(FeedbackController.class.getName());

    @Autowired
    private IFeedbackService feedbackService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     * 客户管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/page")
    public String person() throws Exception{
        return "redirect:/page/feedback/list.jsp";
    }

    /**
     * 客户列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map feedbackList(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String beginTime,String endTime,String status)throws Exception
    {
        PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));
        FeedbackEx feedbackEx=new FeedbackEx();

        if(StringUtil.isNotEmpty(beginTime)) {

               feedbackEx.setBeginTime(DateUtil.convert2Date(beginTime,"yyyy-MM-dd HH:mm:ss"));

        }
        if(StringUtil.isNotEmpty(endTime)) {

            feedbackEx.setEndTime(DateUtil.convert2Date(endTime,"yyyy-MM-dd HH:mm:ss"));

        }
        if(StringUtil.isNotEmpty(status)) {
            if(!status.equals("-1")){
                feedbackEx.setStatus(status);
            }
        }
        List<Feedback> list=feedbackService.getFeedbackPageByEntity(feedbackEx);
        PageInfo<Feedback>p=new PageInfo<Feedback>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }






    /**
     * 客户编辑
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/modify")
    public @ResponseBody
    Map modify(String id,String remark)throws Exception
    {
        Map result=new HashMap();
        Feedback feedback=new Feedback();
        feedback.setId(id);

        feedback.setStatus("1");


        if (StringUtil.isNotEmpty(remark)){
          feedback.setRemark(remark);
        }
        feedback.setDealTime(new Date());
        feedbackService.modifyFeedback(feedback);
        result.put("result","success");
        return result;
    }



    public IFeedbackService getFeedbackService() {
        return feedbackService;
    }

    public void setFeedbackService(IFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
}
