package com.cn.dao;

import com.cn.model.ScheduleJob;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IScheduleJobDao {


    public List<ScheduleJob> pageList(ScheduleJob scheduleJob);
    public List<ScheduleJob> allList();
    public void insert(ScheduleJob scheduleJob);
    public void delete(String scheduleJobId);
    public void update(ScheduleJob scheduleJob);
    public ScheduleJob find(ScheduleJob scheduleJob);



}