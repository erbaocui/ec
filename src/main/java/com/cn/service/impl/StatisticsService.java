package com.cn.service.impl;

import com.cn.dao.IStatisticsDao;
import com.cn.dao.IUserDao;
import com.cn.model.User;
import com.cn.service.IStatisticsService;
import com.cn.service.IUserService;
import com.cn.vo.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("statisticsService")
public class StatisticsService implements IStatisticsService {


    @Autowired
    private IStatisticsDao statisticsDao;

    @Override
    public List<Statistics> getDay(String customerId) {
        return statisticsDao.day( customerId);
    }

    @Override
    public List<Statistics> getMonth(String customerId) {
        return statisticsDao.month(customerId);
    }

    @Override
    public List<Statistics> getWeek(String customerId) {
        return statisticsDao.week( customerId);
    }

    public IStatisticsDao getStatisticsDao() {
        return statisticsDao;
    }

    public void setStatisticsDao(IStatisticsDao statisticsDao) {
        this.statisticsDao = statisticsDao;
    }
}
