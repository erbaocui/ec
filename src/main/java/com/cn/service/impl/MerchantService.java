package com.cn.service.impl;

import com.cn.dao.IMerchantDao;
import com.cn.model.Merchant;
import com.cn.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("merchantService")
public class MerchantService implements IMerchantService {


    @Autowired
    private IMerchantDao merchantDao;



    @Override
    public List<Merchant> getMerchantPageByEntity(Merchant merchant){
        return merchantDao.pageList(merchant);
    }
    @Override
    public Merchant getMerchantByEntity(Merchant merchant) {
        return merchantDao.find(merchant);
    }
    @Override
    public void addMerchant(Merchant merchant) {
        merchantDao.insert(merchant);
    }

    @Override
    public void modifyMerchant(Merchant merchant) {
        merchantDao.update(merchant);
    }



    public IMerchantDao getMerchantDao() {
        return merchantDao;
    }

    public void setMerchantDao(IMerchantDao merchantDao) {
        this.merchantDao = merchantDao;
    }

}
