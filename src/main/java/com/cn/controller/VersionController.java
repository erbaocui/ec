package com.cn.controller;

import com.cn.model.Version;
import com.cn.service.IVersionService;
import com.cn.util.DateUtil;
import com.cn.util.IdGenerator;
import com.cn.util.MD5Util;
import com.cn.util.StringUtil;
import com.cn.vo.VersionEx;
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
@RequestMapping("/version")
@Scope("prototype")
public class VersionController extends BaseController{

    Logger logger= Logger.getLogger(VersionController.class.getName());

    @Autowired
    private IVersionService versionService;


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
        return "redirect:/page/version/list.jsp";
    }

    /**
     * 客户列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map versionList(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,String type,String status,HttpSession session)throws Exception
    {
        PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));
        VersionEx v=new VersionEx();
        if(StringUtil.isNotEmpty(status)) {
            if(!status.equals("-1")) {
                v.setStatus(status);
            }
        }
        if(StringUtil.isNotEmpty(type)) {
            if(!type.equals("-1")) {
                v.setType(type);
            }
        }
        List<Version> list=versionService.getVersionPageByEntity(v);
        PageInfo<Version>p=new PageInfo<Version>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }



    /**
     * 客户查询
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/exist")
    public @ResponseBody
    Map exsit(String type,String v,String noid)throws Exception
    {
        Map result=new HashMap();
        Boolean flag=false;
        VersionEx version=new VersionEx();
        version.setType(type);
        version.setV(v);
        if(!StringUtil.isEmpty(noid)){
            version.setNoid(noid);
        }
        Version u=versionService.getVersionByEntity(version);
        if(null!=u){
            flag=true;
        }
        result.put("result",flag);
        return result;
    }

    /**
     * 客户查询
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody
    Map delete(String id)throws Exception
    {
        Map result=new HashMap();

        Version v=new Version();
        v.setId(id);
        versionService.deleteVersion(v);
        result.put("result","success");
        return result;
    }

    /**
     * 客户查询
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/effect")
    public @ResponseBody
    Map effect(String id)throws Exception
    {
        Map result=new HashMap();

        VersionEx version=new VersionEx();
        version.setId(id);
        Version v=versionService.getVersionByEntity(version);
        versionService.modifyEffectVersion(v);
        result.put("result","success");
        return result;
    }


    /**
     * 客户添加
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/add")
    public @ResponseBody
    Map add(String name,String v,String url,String type,String remark)throws Exception {
        Map result = new HashMap();
        Version version = new Version();

        version.setId(IdGenerator.getId());
        if (StringUtil.isNotEmpty(name)){
            version.setName(name);
        }
        if (StringUtil.isNotEmpty(v)){
            version.setV(v);
        }
        if (StringUtil.isNotEmpty(type)){
            version.setType(type);
        }
        if (StringUtil.isNotEmpty( url)){
            version.setUrl(url);
        }

        if (StringUtil.isNotEmpty(remark)){
            version.setRemark(remark);
        }
        version.setCreateTime(new Date());
        version.setStatus("1");

        versionService.addVersion(version);
        result.put("result","success");
         return result;
    }

    /**
     * 客户编辑
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/modify")
    public @ResponseBody
    Map modify(String id,String name,String url,String remark)throws Exception
    {
        Map result=new HashMap();
        Version v=new Version();
        v.setId(id);
        if (StringUtil.isNotEmpty(name)){
            v.setName(name);
        }
        if (StringUtil.isNotEmpty( url)){
            v.setUrl(url);
        }

        if (StringUtil.isNotEmpty(remark)){
            v.setRemark(remark);
        }

        versionService.modifyVersion(v);
        result.put("result","success");
        return result;
    }



    public IVersionService getVersionService() {
        return versionService;
    }

    public void setVersionService(IVersionService versionService) {
        this.versionService = versionService;
    }
}
