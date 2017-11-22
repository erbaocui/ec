package com.cn.controller;

import com.cn.constant.CustomerType;
import com.cn.constant.Status;
import com.cn.model.Customer;
import com.cn.model.Customer;
import com.cn.service.ICustomerService;
import com.cn.util.DateUtil;
import com.cn.util.ExcelExportUtil;
import com.cn.util.MD5Util;
import com.cn.util.StringUtil;
import com.cn.view.ViewExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/customer")
@Scope("prototype")
public class CustomerController extends BaseController{

    Logger logger= Logger.getLogger(CustomerController.class.getName());

    @Autowired
    private ICustomerService customerService;


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
    @RequestMapping(value="/customer")
    public String person() throws Exception{
        return "redirect:/page/customer/list.jsp";
    }
    /**
     * 企业客户管理
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value="/enterprise")
    public String enterprise() throws Exception{
        return "redirect:/page/customer/enterpriseList.jsp";
    }
    /**
     * 客户列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map customerList(@RequestParam(value="page", required=false) String page,
                 @RequestParam(value="rows", required=false) String rows,
            String loginName,String displayName,String status,String type,HttpSession session)throws Exception
    {
        PageHelper.startPage(Integer.valueOf(page) ,Integer.valueOf(rows));
        Customer customer=new Customer();
        if(StringUtil.isNotEmpty(loginName)) {
            customer.setLoginName(loginName);
            session.setAttribute("loginName",loginName);
        }
        if(StringUtil.isNotEmpty(displayName)) {
            customer.setDisplayName(displayName);
            session.setAttribute("displayName",displayName);
        }
        if(StringUtil.isNotEmpty(status)) {
           if(!status.equals("-1")){
               customer.setStatus(status);
               session.setAttribute("status",status);
           }
        }
        customer.setType(type);
        List<Customer> list=customerService.getCustomerPageByEntity(customer);
        PageInfo<Customer>p=new PageInfo<Customer>(list);
        Map map=new HashMap();
        map.put("total", Long.toString(p.getTotal()));
        map.put("rows",list);
     /*   map.put("total", 0);
        map.put("rows",new ArrayList());*/
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
    Map exist(String loginName)throws Exception
    {
        Map result=new HashMap();
        Boolean flag=false;
        Customer customer=new Customer();
        customer.setLoginName(loginName);
        Customer u=customerService.getCustomerByEntity(customer);
        if(null!=u){
            flag=true;
        }
        result.put("result",flag);
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
    Map add(String loginName,String displayName,String password,String gender,String birthdate,String  cmbProvince,String cmbCity,String  cmbArea,String remark)throws Exception {
        Map result = new HashMap();
        Customer customer = new Customer();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        customer.setId(uuid);
        customer.setLoginName(loginName);
        customer.setDisplayName(displayName);
        customer.setPassword(MD5Util.md5(password));
        customer.setType("0");
        if (StringUtil.isNotEmpty(gender)){
            customer.setGender(gender);
        }
        if (StringUtil.isNotEmpty( birthdate)){
            customer.setBirthdate(DateUtil.convert2Date(birthdate,"yyyy-MM-dd"));
        }
        if (StringUtil.isNotEmpty(cmbProvince)){
            customer.setProvince(cmbProvince);
        }
        if (StringUtil.isNotEmpty(cmbCity)){
            customer.setCity(cmbCity);
        }
        if (StringUtil.isNotEmpty( cmbArea)){
            customer.setCounty(cmbArea);
        }

        if (StringUtil.isNotEmpty(remark)){
            customer.setRemark(remark);
        }
        customer.setStatus("0");

        customerService.addCustomer(customer);
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
    Map modify(String id,String displayName,String password,String gender,String birthdate,String  cmbProvince,String cmbCity,String  cmbArea,String remark,String status)throws Exception
    {
        Map result=new HashMap();
        Customer customer=new Customer();
        customer.setId(id);
        customer.setDisplayName(displayName);
        if (StringUtil.isNotEmpty(gender)){
            customer.setGender(gender);
        }
        if (StringUtil.isNotEmpty( birthdate)){
            customer.setBirthdate(DateUtil.convert2Date(birthdate, "yyyy-MM-dd"));
        }
        if (StringUtil.isNotEmpty(cmbProvince)){
            customer.setProvince(cmbProvince);
        }
        if (StringUtil.isNotEmpty(cmbCity)){
            customer.setCity(cmbCity);
        }
        if (StringUtil.isNotEmpty( cmbArea)){
            customer.setCounty(cmbArea);
        }

        if (StringUtil.isNotEmpty(remark)) {
            customer.setRemark(remark);
        }
        if (StringUtil.isNotEmpty(status)){
          customer.setStatus(status);
        }
        if (StringUtil.isNotEmpty(password)){
                customer.setPassword(MD5Util.md5(password));
        }

        customerService.modifyCustomer(customer);
        result.put("result","success");
        return result;
    }



    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }
}
