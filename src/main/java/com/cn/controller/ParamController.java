package com.cn.controller;

import com.cn.model.Role;
import com.cn.model.Param;
import com.cn.service.IParamService;
import com.cn.util.MD5Util;
import com.cn.util.StringUtil;
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
@RequestMapping("/param")
@Scope("prototype")
public class ParamController extends BaseController{

    Logger logger= Logger.getLogger(ParamController.class.getName());

    @Autowired
    private IParamService paramService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     *参数设置
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/page")
    public String param() throws Exception{
        return "redirect:/page/param/param.jsp";
    }

    /**
     * 参数列表
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map list(String type)throws Exception
    {

        Param param=new Param();
        List<Param> list=new ArrayList<Param>();
        if(type.equals("0")){
            list=paramService.getAppParamList(param);
        }
        if(type.equals("1")){
            list=paramService.getShareParamList(param);
        }
        if(type.equals("2")){
            list=paramService.getMallParamList(param);
        }

        Map map=new HashMap();
        map.put("rows",list);
        map.put("total",list.size());
        return map;
    }






    /**
     * 参数编辑
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/modify")
    public @ResponseBody
    Map modify(String[] kvs,String[] values)throws Exception
    {
        for(int i=0;i<kvs.length;i++) {
            Param param=new Param();
            param.setKv(kvs[i]);
            param.setValue(values[i]);
            paramService.modifyParam(param);
        }
        Map result=new HashMap();
        result.put("result","success");
        return result;
    }

    public IParamService getParamService() {
        return paramService;
    }

    public void setParamService(IParamService paramService) {
        this.paramService = paramService;
    }
}
