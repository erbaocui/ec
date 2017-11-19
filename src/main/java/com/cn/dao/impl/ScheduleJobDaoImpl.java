package com.cn.dao.impl;

import com.cn.dao.IScheduleJobDao;
import com.cn.model.ScheduleJob;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("scheduleJobDao")
public class ScheduleJobDaoImpl extends BaseDaoImpl implements IScheduleJobDao {

    @Override
    public List<ScheduleJob> pageList(ScheduleJob scheduleJob) {
        return (List<ScheduleJob>)list("com.cn.dao.ScheduleJobMapper.selectPageByEntity", scheduleJob);

    }

    @Override
    public List<ScheduleJob> allList() {
        return (List<ScheduleJob>)list("com.cn.dao.ScheduleJobMapper.selectAll", "");
    }

    @Override
    public void insert(ScheduleJob scheduleJob) {
        addObject("com.cn.dao.ScheduleJobMapper.insert", scheduleJob);
    }


    @Override
    public ScheduleJob find(ScheduleJob scheduleJob) {
        return (ScheduleJob)findObject("com.cn.dao.ScheduleJobMapper.selectOneByEntity", scheduleJob);
    }

    @Override
    public void update(ScheduleJob scheduleJob) {
         updateObject("com.cn.dao.ScheduleJobMapper.updateByPK",scheduleJob);
    }

    @Override
    public void delete(String scheduleJobId) {
        deleteObject("com.cn.dao.ScheduleJobMapper.deleteByPK", scheduleJobId);
    }
}
