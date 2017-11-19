package com.cn.dao.impl;

import com.cn.dao.IMerchantDao;
import com.cn.model.Merchant;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("merchantDao")
public class MerchantDaoImpl extends BaseDaoImpl implements IMerchantDao {

    @Override
    public List<Merchant> pageList(Merchant merchant) {
        return (List<Merchant>)list("com.cn.dao.MerchantMapper.selectPageByEntity", merchant);

    }

    @Override
    public void insert(Merchant merchant) {
        addObject("com.cn.dao.MerchantMapper.insert", merchant);
    }

    @Override
    public void update(Merchant merchant) {
        updateObject("com.cn.dao.MerchantMapper.updateByPrimaryKey", merchant);
    }

    @Override
    public Merchant find(Merchant merchant) {
        return (Merchant)findObject("com.cn.dao.MerchantMapper.selectOneByEntity", merchant);
    }
}
