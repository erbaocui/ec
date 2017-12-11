package com.cn.service;

import com.cn.model.Commodity;
import com.cn.vo.CommodityEx;

import java.util.List;

public interface ICommodityService {
    //用户
    public List<Commodity> getCommodityPageByEntity(Commodity commodity);
    public Commodity getCommodityByEntity(CommodityEx commodityEx);
    public void addCommodity(Commodity commodity);
    public void modifyCommodity(Commodity commodity);

}
