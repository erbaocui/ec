package com.cn.service.impl;

import com.cn.dao.IUserDao;
import com.cn.model.User;
import com.cn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("testService")
public class TestService {







    public void test1(){
      while(1==1){

      }
    }

    public void test2(){
        try {
            Thread.sleep(15*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("**************test299998777777777777*********");
    }



}
