package com.cn.service;




import com.cn.model.ScheduleJob;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 
 * @Description: 计划任务管理
 * @author lizhenjie
 * @date 2017年4月25日 下午2:43:54
 */

public interface IJobTaskService {

	/**
	 * 从数据库中取 区别于getAllJob
	 * 
	 * @return
	 */
	public List<ScheduleJob> getTaskPageList(ScheduleJob job);

	/**
	 * 添加到数据库中 区别于addJob
	 */
	public void addTask(ScheduleJob job);

	/**
	 * 从数据库中查询job
	 */
	public ScheduleJob getOneTask( ScheduleJob  scheduleJob);
	/**
	 * 删除 区别于deleteJob
	 */
	public void deleteTask( ScheduleJob  scheduleJob);
	/**
	 * 从数据库中查询job
	 */
	public ScheduleJob getTaskById( String jobId );

	/**
	 * 更改任务状态
	 * 
	 * @throws org.quartz.SchedulerException
	 */
	public void changeStatus(String jobId, String cmd) throws SchedulerException;

	/**
	 * 更改任务 cron表达式
	 *
	 * @throws org.quartz.SchedulerException
	 */
	public void updateCron(String jobId, String cron) throws SchedulerException;

	/**
	 * 添加任务
	 *
	 * @param
	 * @throws org.quartz.SchedulerException
	 */
	public void addJob(ScheduleJob job) throws SchedulerException;


	/**
	 * 获取所有计划中的任务列表
	 *
	 * @return
	 * @throws org.quartz.SchedulerException
	 */
	public List<ScheduleJob> getAllJob() throws SchedulerException;
	/**
	 * 获取所有计划中的任务列表
	 *
	 * @return
	 * @throws org.quartz.SchedulerException
	 */

	public ScheduleJob getJobStatus(ScheduleJob scheduleJob) throws SchedulerException;

	/**
	 * 所有正在运行的job
	 *
	 * @return
	 * @throws org.quartz.SchedulerException
	 */
	public List<ScheduleJob> getRunningJob() throws SchedulerException ;

	/**
	 * 暂停一个job
	 *
	 * @param scheduleJob
	 * @throws org.quartz.SchedulerException
	 */
	public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException ;

	/**
	 * 恢复一个job
	 *
	 * @param scheduleJob
	 * @throws org.quartz.SchedulerException
	 */
	public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException ;
	/**
	 * 删除一个job
	 *
	 * @param scheduleJob
	 * @throws org.quartz.SchedulerException
	 */
	public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException ;

	/**
	 * 立即执行job
	 *
	 * @param scheduleJob
	 * @throws org.quartz.SchedulerException
	 */
	public void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException ;
	/**
	 * 更新job时间表达式
	 *
	 * @param scheduleJob
	 * @throws org.quartz.SchedulerException
	 */
	public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException ;
	/**
	 * 更新job时间表达式
	 *
	 * @param scheduleJob
	 * @throws org.quartz.SchedulerException
	 */
	public void updateTask(ScheduleJob scheduleJob) ;


}
