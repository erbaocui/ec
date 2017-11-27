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
import java.net.URLDecoder;
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

    public static void main(String args[])throws Exception{
        String param="http://47.95.1.213:8080/ecapi/customer/update.do?lang=zh&q=%7B%22name%22%3A%22mlj04%22%2C%22county%22%3A%22%E5%BE%90%E6%B1%87%E5%8C%BA%22%2C%22header%22%3A%22http%3A%5C%2F%5C%2Foyz6r2siu.bkt.clouddn.com%5C%2Fuserheader_android_c0c52a0234cc4b3bb9584ddd3083268b_201711271839272770.jpg%22%2C%22gender%22%3A0%2C%22province%22%3A%22%E4%B8%8A%E6%B5%B7%22%2C%22birthdate%22%3A372960000000%2C%22city%22%3A%22%E5%B8%82%E8%BE%96%E5%8C%BA%22%7D";
        System.out.println(URLDecoder.decode(param, "UTF-8"));
    }


}
