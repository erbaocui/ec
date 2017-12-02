package com.cn.quartz;

import com.cn.model.ScheduleJob;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * 
 * @Description: 计划任务执行处 无状态
 * @author lizhenjie
 * @date 2017年4月24日 下午5:05:47
 */
public class QuartzJobFactory  implements Job {
	public final Logger log = Logger.getLogger(this.getClass());




	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}