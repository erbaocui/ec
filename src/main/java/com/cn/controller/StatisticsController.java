package com.cn.controller;

import com.cn.model.Customer;
import com.cn.service.ICustomerService;
import com.cn.service.IStatisticsService;

import com.cn.util.StringUtil;

import com.cn.vo.Statistics;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/statistics")
@Scope("prototype")
public class StatisticsController extends BaseController{

    Logger logger= Logger.getLogger(StatisticsController.class.getName());

    @Autowired
    private IStatisticsService statisticsService;

    @Autowired
    private ICustomerService customerService;



    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     *订单管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/page")
    public String person() throws Exception{
        return "redirect:/page/statistics/list.jsp";
}
   

    @RequestMapping(value = "/list")
    public @ResponseBody
    Map list(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String loginName,String type)throws Exception
    {


        Map map=new HashMap();
        if(StringUtil.isEmpty(loginName)){
            map.put("total","0");
            map.put("rows",new ArrayList());
            return map;
        }
        Customer  customer=new Customer();
        customer.setLoginName(loginName);
        Customer c=customerService.getCustomerByEntity(customer);

       // PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(rows));
        if(c!=null){
            List<Statistics> list=new ArrayList<Statistics>();

            if(type.equals("0")) {
                list = statisticsService.getDay(c.getId());
            }
            if(type.equals("1")) {
                list = statisticsService.getWeek(c.getId());
            }
            if(type.equals("2")) {
                list = statisticsService.getMonth(c.getId());
            }

            //PageInfo<Statistics>p=new PageInfo<Statistics>(list);

            map.put("total", Long.toString(list.size()));
            map.put("rows",list);
        }else{
            map.put("total","0");
            map.put("rows",new ArrayList());
        }

        return map;
    }


}
