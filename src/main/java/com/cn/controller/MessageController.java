package com.cn.controller;

import com.cn.model.Message;
import com.cn.model.Role;
import com.cn.model.Message;
import com.cn.service.IMessageService;
import com.cn.three.jpush.JpushUtil;
import com.cn.util.DateUtil;
import com.cn.util.IdGenerator;
import com.cn.util.MD5Util;
import com.cn.util.StringUtil;
import com.cn.vo.MessageEx;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/message")
@Scope("prototype")
public class MessageController extends BaseController{

    Logger logger= Logger.getLogger(MessageController.class.getName());

    @Autowired
    private IMessageService messageService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     * 消息管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/page")
    public String message() throws Exception{
        return "redirect:/page/message/list.jsp";
    }

    /**
     * 消息列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map list(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
                 String beginTime,String endTime,String status)throws Exception
    {
        PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));
        MessageEx messageEx=new MessageEx();

        if(StringUtil.isNotEmpty(beginTime)) {

            messageEx.setBeginTime(DateUtil.convert2Date(beginTime, "yyyy-MM-dd HH:mm:ss"));

        }
        if(StringUtil.isNotEmpty(endTime)) {

            messageEx.setEndTime(DateUtil.convert2Date(endTime,"yyyy-MM-dd HH:mm:ss"));

        }
        if(StringUtil.isNotEmpty(status)) {
            if(!status.equals("-1")){
                messageEx.setStatus(status);
            }
        }
        List<Message> list=messageService.getMessagePageByEntity(messageEx);
        PageInfo<Message>p=new PageInfo<Message>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }

    /**
     * 消息查询
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/exist")
    public @ResponseBody
    Map exist(String loginName)throws Exception
    {
        Map result=new HashMap();
        Boolean flag=false;
        Message message=new Message();

        Message u=messageService.getMessageByEntity(message);
        if(null!=u){
            flag=true;
        }
        result.put("result",flag);
        return result;
    }


    /**
     * 消息添加
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/add")
    public @ResponseBody
    Map add(String title,String content,String picture,String url)throws Exception
    {
        Map result=new HashMap();
        Message message=new Message();
        message.setId(IdGenerator.getId());
        message.setTitle(title);
        message.setContent(content);
        message.setUrl(url);
        message.setPicture(picture);
       try{
             JpushUtil.jpushAndroid(title,content,message.getId());
             message.setStatus("1");
        }catch (Exception  e){
           message.setStatus("0");
       }
        message.setSendTime(new Date());
        messageService.addMessage(message);
        result.put("result","success");
         return result;
    }



    public IMessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }
}
