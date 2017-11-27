package com.cn.controller;

import com.cn.constant.Status;
import com.cn.model.Commodity;
import com.cn.service.ICommodityService;
import com.cn.util.IdGenerator;
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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/commodity")
@Scope("prototype")
public class CommodityController extends BaseController{

    Logger logger= Logger.getLogger(CommodityController.class.getName());

    @Autowired
    private ICommodityService commodityService;


    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     * 商品管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/commodity")
    public String commodity() throws Exception{
        return "redirect:/page/commodity/list.jsp";
    }

    /**
     * 商品列表查询
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
        Commodity commodity=new Commodity();
        if(StringUtil.isNotEmpty( name)){
            commodity.setName(name);
        }

        if(StringUtil.isNotEmpty(status)) {
            if(!status.equals("-1")){
                commodity.setStatus(status);
            }
        }
        List<Commodity> list=commodityService.getCommodityPageByEntity(commodity);

        PageInfo<Commodity>p=new PageInfo<Commodity>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
        return map;
    }

    /**
     * 商品查询
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
        Commodity commodity=new Commodity();
        commodity.setName(name);
        Commodity u=commodityService.getCommodityByEntity(commodity);
        if(null!=u){
            flag=true;
        }
        result.put("result",flag);
        return result;
    }


    /**
     * 商品添加
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/add")
    public @ResponseBody
    Map add(String name,String brief,String remark,BigDecimal price,Integer seq,String urlJd,String urlTmall,String certified,
            String speedRefund,String sevenReturn)throws Exception
    {
        Map result=new HashMap();
        Commodity commodity=new Commodity();

        commodity.setId(IdGenerator.getId());
        commodity.setName(name);
        commodity.setPrice( price);
        commodity.setSeq(seq);
        if(StringUtil.isNotEmpty(brief)) {
            commodity.setBrief(brief);
        }
        
        if(StringUtil.isNotEmpty( remark)) {
            commodity.setRemark(remark);
        }

        if(StringUtil.isNotEmpty( urlJd)) {
            commodity.setUrlJd( urlJd);
        }
        if(StringUtil.isNotEmpty( urlTmall)) {
            commodity.setUrlTmall( urlTmall);
        }
        commodity.setStatus(String.valueOf(Status.EFFECTIVE.getIndex()));
        commodity.setCertified(certified);
        commodity.setSpeedRefund(speedRefund);
        commodity.setSevenReturn(sevenReturn);
        commodityService.addCommodity(commodity);
        result.put("result","success");
         return result;
    }

    /**
     * 商品编辑
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/modify")
    public @ResponseBody
    Map modify(String id,String status,String brief,String remark,String urlJd,String urlTmall,String certified,
               String speedRefund,String sevenReturn)throws Exception
    {
        Map result=new HashMap();
        Commodity commodity=new Commodity();

        commodity.setId(id);
        if(StringUtil.isNotEmpty(brief)) {
            commodity.setBrief(brief);
        }

        if(StringUtil.isNotEmpty( remark)) {
            commodity.setRemark(remark);
        }
        if(StringUtil.isNotEmpty(status)) {
            commodity.setStatus(status);
        }
        if(StringUtil.isNotEmpty( urlJd)) {
            commodity.setUrlJd( urlJd);
        }
        if(StringUtil.isNotEmpty( urlTmall)) {
            commodity.setUrlTmall( urlTmall);
        }
        commodity.setCertified(certified);
        commodity.setSpeedRefund(speedRefund);
        commodity.setSevenReturn(sevenReturn);
        commodityService.modifyCommodity(commodity);
        result.put("result","success");
        return result;
    }

    public ICommodityService getCommodityService() {
        return commodityService;
    }

    public void setCommodityService(ICommodityService commodityService) {
        this.commodityService = commodityService;
    }
}
