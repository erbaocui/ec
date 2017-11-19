package com.cn.controller;

import com.cn.constant.FileUploadDic;
import com.cn.constant.Qiniu;
import com.cn.service.IQiniuService;
import com.cn.util.IdGenerator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by 13 on 2017/4/7.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private IQiniuService qiniuService;
    /**
     *
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="upload",method=RequestMethod.POST,produces="text/html;charset=utf-8")
    public String uploadFile(@RequestParam(value = "Filedata",required=false)List<CommonsMultipartFile> files,HttpServletRequest req,HttpServletResponse response) throws Exception{

//设置文件保存的本地路径

       // String filePath = req.getSession().getServletContext().getRealPath("/uploadFiles/");


        MultipartFile file =files.get(0);

        String fileName =files.get(0).getOriginalFilename();

        String fileType = fileName.split("[.]")[1];

//为了避免文件名重复，在文件名前加UUID


        String uuidFileName = IdGenerator.getId()+"."+fileType;

        qiniuService.upload(file.getBytes(), uuidFileName);
        response.getWriter().print(Qiniu.DOMAIN_NAME+"/"+uuidFileName);
        return null;

    }

    public IQiniuService getQiniuService() {
        return qiniuService;
    }

    public void setQiniuService(IQiniuService qiniuService) {
        this.qiniuService = qiniuService;
    }
}
