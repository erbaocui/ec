package com.cn.controller;

import com.cn.model.Role;
import com.cn.model.User;
import com.cn.service.IUserService;
import com.cn.util.MD5Util;
import com.cn.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/test")
@Scope("prototype")
public class TestController extends BaseController{

    Logger logger= Logger.getLogger(TestController.class.getName());



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
    @RequestMapping(value="/test")
    public String welcome(){
        return "test";
    }

    @RequestMapping(value="changeLang")
    public String changeLanguage(Model model, HttpServletRequest request,HttpServletResponse response,HttpSession session){
       WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
       // String testMsg = applicationContext.getMessage("test.msg", null, locale);
        //ResourceBundle myResources = ResourceBundle.getBundle("test.msg", locale);
       // String aha = myResources.getString("test.msg");
        RequestContext requestContext = new RequestContext(request);
        System.out.println(requestContext.getMessage("test.msg"));
        //model.addAttribute("i18n_msg",testMsg);*/
        return "test";


    }


}
