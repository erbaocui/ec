package com.cn.service;

import com.cn.model.Merchant;

import java.util.List;

public interface IMerchantService {
    //用户
    public List<Merchant> getMerchantPageByEntity(Merchant merchant);
    public Merchant getMerchantByEntity(Merchant merchant);
    public void addMerchant(Merchant merchant);
    public void modifyMerchant(Merchant merchant);

}
