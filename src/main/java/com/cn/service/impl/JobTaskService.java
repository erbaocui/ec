package com.cn.service.impl;

import com.cn.constant.ScheduleJobStatus;
import com.cn.constant.Status;
import com.cn.dao.IScheduleJobDao;
import com.cn.model.ScheduleJob;
import com.cn.quartz.QuartzJobFactory;
import com.cn.quartz.QuartzJobFactoryDisallowConcurrentExecution;
import com.cn.service.IJobTaskService;

import com.cn.util.SpringUtils;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 
 * @Description: 计划任务管理
 * @author lizhenjie
 * @date 2017年4月25日 下午2:43:54
 */
@Service("jobTaskService")
public class JobTaskService implements IJobTaskService {
	public final Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private IScheduleJobDao scheduleJobDao;

	/**
	 * 从数据库中取 区别于getAllJob
	 * 
	 * @return
	 */
	public List<ScheduleJob> getTaskPageList(ScheduleJob job) {

		return scheduleJobDao.pageList(job);
	}

	/**
	 * 添加到数据库中 区别于addJob
	 */
	public void addTask(ScheduleJob job) {
		job.setCreateTime(new Date());
		scheduleJobDao.insert(job);
	}

	/**
	 * 从数据库中查询job
	 */
	public ScheduleJob getOneTask(ScheduleJob  scheduleJob) {

		return scheduleJobDao.find( scheduleJob);
	}

	/**
	 * 从数据库中查询job
	 */
	public ScheduleJob getTaskById(String  jobId) {
		ScheduleJob  scheduleJob=new ScheduleJob();
		scheduleJob.setJobId(jobId);
		return scheduleJobDao.find( scheduleJob);
	}

	/**
	 * 更改任务状态
	 * 
	 * @throws org.quartz.SchedulerException
	 */
	public void changeStatus(String  jobId, String cmd) throws SchedulerException {
		ScheduleJob job = getTaskById(jobId);
		if (job == null) {
			return;
		}
		if ("stop".equals(cmd)) {
			deleteJob(job);
			job.setJobStatus(String.valueOf(Status.INVALID.getIndex()));
		} else if ("start".equals(cmd)) {
			job.setJobStatus(String.valueOf(Status.EFFECTIVE.getIndex()));
			addJob(job);
		}
		scheduleJobDao.update(job);
	}

	/**
	 * 更改任务 cron表达式
	 *
	 * @throws org.quartz.SchedulerException
	 */
	public void updateCron(String jobId, String cron) throws SchedulerException {
		ScheduleJob job = getTaskById(jobId);
		if (job == null) {
			return;
		}
		job.setCronExpression(cron);
		if (String.valueOf(Status.EFFECTIVE.getIndex()).equals(job.getJobStatus())) {
			updateJobCron(job);
		}
		scheduleJobDao.update(job);

	}

	/**
	 * 添加任务
	 *
	 * @param
	 * @throws org.quartz.SchedulerException
	 */
	public void addJob(ScheduleJob job) throws SchedulerException {
		if (job == null || !String.valueOf(Status.EFFECTIVE.getIndex()).equals(job.getJobStatus())) {
			return;
		}

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		log.debug(scheduler + ".......................................................................................add");
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			Class clazz = ScheduleJobStatus.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;

			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();

			jobDetail.getJobDataMap().put("scheduleJob", job);

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	/**
	 * 删除一个task
	 *
	 * @param scheduleJob
	 * @throws org.quartz.SchedulerException
	 */
	public void deleteTask(ScheduleJob scheduleJob) {

		scheduleJobDao.delete(scheduleJob.getJobId());
	}

	@PostConstruct
	public void init() throws Exception {

		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		// 这里获取任务信息数据
		List<ScheduleJob> jobList = scheduleJobDao.allList();

		for (ScheduleJob job : jobList) {
			addJob(job);
		}
	}

	/**
	 * 获取所有计划中的任务列表
	 *
	 * @return
	 * @throws org.quartz.SchedulerException
	 */
	public List<ScheduleJob> getAllJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleJob job = new ScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDescription("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}


	/**
	 * 获取所有计划中的任务列表
	 *
	 * @return
	 * @throws org.quartz.SchedulerException
	 */
	public ScheduleJob getJobStatus(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleJob job = new ScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDescription("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				if((job.getJobGroup().equals(scheduleJob.getJobGroup()))&&(job.getJobName().equals(scheduleJob.getJobName())))
				{
					scheduleJob.setIsConcurrent(String.valueOf(triggerState.ordinal()));
					System.out.println(triggerState.name());
					return scheduleJob;
				}
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return scheduleJob;
	}


	/**
	 * 所有正在运行的job
	 *
	 * @return
	 * @throws org.quartz.SchedulerException
	 */
	public List<ScheduleJob> getRunningJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			ScheduleJob job = new ScheduleJob();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			job.setDescription("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}

	/**
	 * 暂停一个job
	 *
	 * @param scheduleJob
	 * @throws org.quartz.SchedulerException
	 */
	public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个job
	 *
	 * @param scheduleJob
	 * @throws org.quartz.SchedulerException
	 */
	public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除一个job
	 *
	 * @param scheduleJob
	 * @throws org.quartz.SchedulerException
	 */
	public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);

	}

	/**
	 * 立即执行job
	 *
	 * @param scheduleJob
	 * @throws org.quartz.SchedulerException
	 */
	public void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新job时间表达式
	 *
	 * @param scheduleJob
	 * @throws org.quartz.SchedulerException
	 */
	public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());

		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		scheduler.rescheduleJob(triggerKey, trigger);
	}

	@Override
	public void updateTask(ScheduleJob scheduleJob) {
		scheduleJobDao.update(scheduleJob);
	}

	public static void main(String[] args) {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("xxxxx");
	}
}
