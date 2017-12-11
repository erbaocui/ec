package com.cn.dao;

import com.cn.model.Param;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IParamDao {


    public List<Param> applist(Param param);
    public List<Param> sharelist(Param param);
    public List<Param> malllist(Param param);
    public void insert(Param param);
    public void update(Param param);
    public Param find(Param param);


}