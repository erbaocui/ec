package com.cn.service.impl;

import com.cn.dao.ICustomerDeviceDao;
import com.cn.model.CustomerDevice;
import com.cn.service.ICustomerDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("customerDeviceService")
public class CustomerDeviceService implements ICustomerDeviceService {


    @Autowired
    private ICustomerDeviceDao customerDeviceDao;



    @Override
    public List<CustomerDevice> getCustomerDevicePageByEntity(CustomerDevice customerDevice){
        return customerDeviceDao.pageList(customerDevice);
    }
    @Override
    public CustomerDevice getCustomerDeviceByEntity(CustomerDevice customerDevice) {
        return customerDeviceDao.find(customerDevice);
    }
    @Override
    public void addCustomerDevice(CustomerDevice customerDevice) {
        customerDeviceDao.insert(customerDevice);
    }

    @Override
    public void modifyCustomerDevice(CustomerDevice customerDevice) {
        customerDeviceDao.update(customerDevice);
    }



    public ICustomerDeviceDao getCustomerDeviceDao() {
        return customerDeviceDao;
    }

    public void setCustomerDeviceDao(ICustomerDeviceDao customerDeviceDao) {
        this.customerDeviceDao = customerDeviceDao;
    }

}
