package com.cn.dao;

import com.cn.model.Merchant;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IMerchantDao {


    public List<Merchant> pageList(Merchant merchant);
    public void insert(Merchant merchant);
    public void update(Merchant merchant);
    public Merchant find(Merchant merchant);

}