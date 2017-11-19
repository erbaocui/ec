package com.cn.controller;

import com.cn.constant.Status;
import com.cn.model.Role;
import com.cn.model.Merchant;
import com.cn.service.IMerchantService;
import com.cn.util.IdGenerator;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/merchant")
@Scope("prototype")
public class MerchantController extends BaseController{

    Logger logger= Logger.getLogger(MerchantController.class.getName());

    @Autowired
    private IMerchantService merchantService;


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
    @RequestMapping(value="/merchant")
    public String merchant() throws Exception{
        return "redirect:/page/merchant/list.jsp";
    }

    /**
     * 用户列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map list(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String name,String status)throws Exception
    {
        PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));
        Merchant merchant=new Merchant();
        if(StringUtil.isNotEmpty( name)){
            merchant.setName(name);
        }

        if(StringUtil.isNotEmpty(status)) {
            if(!status.equals("-1")){
                merchant.setStatus(status);
            }
        }
        List<Merchant> list=merchantService.getMerchantPageByEntity(merchant);

        PageInfo<Merchant>p=new PageInfo<Merchant>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }

    /**
     * 用户查询
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/exist")
    public @ResponseBody
    Map exist(String name)throws Exception
    {
        Map result=new HashMap();
        Boolean flag=false;
        Merchant merchant=new Merchant();
        merchant.setName(name);
        Merchant u=merchantService.getMerchantByEntity(merchant);
        if(null!=u){
            flag=true;
        }
        result.put("result",flag);
        return result;
    }


    /**
     * 用户添加
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/add")
    public @ResponseBody
    Map add(String name,String brief,String phone,String remark)throws Exception
    {
        Map result=new HashMap();
        Merchant merchant=new Merchant();

        merchant.setId(IdGenerator.getId());
        merchant.setName(name);
        if(StringUtil.isNotEmpty(brief)) {
            merchant.setBrief(brief);
        }
        if(StringUtil.isNotEmpty( phone)) {
            merchant.setPhone(phone);
        }
        if(StringUtil.isNotEmpty( remark)) {
            merchant.setRemark(remark);
        }
        merchant.setStatus(String.valueOf(Status.EFFECTIVE.getIndex()));
        merchantService.addMerchant(merchant);
        result.put("result","success");
         return result;
    }

    /**
     * 用户编辑
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/modify")
    public @ResponseBody
    Map modify(String id,String status,String remark,String lat,String lon,String address)throws Exception
    {
        Map result=new HashMap();
        Merchant merchant=new Merchant();

        merchant.setId(id);
        if(StringUtil.isNotEmpty( lat)) {
            merchant.setLat(lat);
        }
        if(StringUtil.isNotEmpty( lon)) {
            merchant.setLon(lon);
        }
        if(StringUtil.isNotEmpty(  address)) {
            merchant.setAddress(address);
        }

        if(StringUtil.isNotEmpty( remark)) {
            merchant.setRemark(remark);
        }
        if(StringUtil.isNotEmpty(status)) {
            merchant.setStatus(status);
        }

        merchantService.modifyMerchant(merchant);
        result.put("result","success");
        return result;
    }

    public IMerchantService getMerchantService() {
        return merchantService;
    }

    public void setMerchantService(IMerchantService merchantService) {
        this.merchantService = merchantService;
    }
}
