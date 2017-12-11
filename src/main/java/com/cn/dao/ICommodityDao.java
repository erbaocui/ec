package com.cn.dao;

import com.cn.model.Commodity;
import com.cn.vo.CommodityEx;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface ICommodityDao {


    public List<Commodity> pageList(Commodity commodity);
    public void insert(Commodity commodity);
    public void update(Commodity commodity);
    public Commodity find(CommodityEx commodityEx);

}