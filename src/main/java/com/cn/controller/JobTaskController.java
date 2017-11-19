package com.cn.controller;


import com.cn.constant.ScheduleJobStatus;
import com.cn.constant.Status;
import com.cn.model.ScheduleJob;
import com.cn.service.IJobTaskService;
import com.cn.service.impl.JobTaskService;
import com.cn.util.SpringContextHolder;
import com.cn.util.SpringUtils;
import com.cn.util.StringUtil;
import com.cn.vo.RetObj;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
public class JobTaskController {
	// 日志记录器
	public final Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private IJobTaskService taskService;

	@RequestMapping(value="/task")
	public String task() throws Exception{
		return "redirect:/page/task/taskList.jsp";
	}

	@RequestMapping("/list")
	public 	@ResponseBody
	Map taskList(@RequestParam(value="page", required=false) String page, @RequestParam(value="rows", required=false) String rows,
				 String jobGroup,String jobName,String jobStatus) throws Exception{
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(rows));
		ScheduleJob job=new ScheduleJob();
		if(StringUtil.isNotEmpty(jobGroup)) {
			job.setJobGroup(jobGroup);

		}
		if(StringUtil.isNotEmpty(jobName)) {
			job.setJobName(jobName);

		}
		if(StringUtil.isNotEmpty(jobStatus)&&!jobStatus.equals("-1")) {
			job.setJobStatus(jobStatus);

		}
		List<ScheduleJob> taskList = taskService.getTaskPageList(job);
		for(int i=0;i<taskList.size();i++){
			ScheduleJob scheduleJob=taskList.get(i);
			scheduleJob=taskService.getJobStatus(scheduleJob);
			taskList.remove(i);
			taskList.add(i,scheduleJob);
		}
		PageInfo<ScheduleJob> p=new PageInfo<ScheduleJob>(taskList);
		Map map=new HashMap();
		map.put("total", Long.toString(p.getTotal()));
		map.put("rows",taskList);


		return map;
	}

	@RequestMapping("addJob")
	 @ResponseBody
	 public RetObj addJob(String jobId) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobId(jobId);
		try {
			scheduleJob = taskService.getOneTask(scheduleJob);
			taskService.addJob(scheduleJob);
			retObj.setCode(Status.EFFECTIVE.getIndex());
		} catch (Exception e) {

			retObj.setCode(Status.INVALID.getIndex());
			retObj.setMessage("加载定时失败！");
			return retObj;
		}

		retObj.setCode(Status.EFFECTIVE.getIndex());
		return retObj;
	}

	@RequestMapping("pauseJob")
	@ResponseBody
	public RetObj pauseJob(String jobId) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobId(jobId);
		try {
			scheduleJob = taskService.getOneTask(scheduleJob);
			taskService.pauseJob(scheduleJob);
			retObj.setCode(Status.EFFECTIVE.getIndex());
		} catch (Exception e) {

			retObj.setCode(Status.INVALID.getIndex());
			retObj.setMessage("加载定时失败！");
			return retObj;
		}

		retObj.setCode(Status.EFFECTIVE.getIndex());
		return retObj;
	}

	@RequestMapping("resumeJob")
	@ResponseBody
	public RetObj resumeJob(String jobId) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobId(jobId);
		try {
			scheduleJob = taskService.getOneTask(scheduleJob);
			taskService.resumeJob(scheduleJob);
			retObj.setCode(Status.EFFECTIVE.getIndex());
		} catch (Exception e) {

			retObj.setCode(Status.INVALID.getIndex());
			retObj.setMessage("加载定时失败！");
			return retObj;
		}

		retObj.setCode(Status.EFFECTIVE.getIndex());
		return retObj;
	}
	@RequestMapping("runJob")
	@ResponseBody
	public RetObj runJob(String jobId) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobId(jobId);
		try {
			scheduleJob = taskService.getOneTask(scheduleJob);
			taskService.runAJobNow(scheduleJob);
			retObj.setCode(Status.EFFECTIVE.getIndex());
		} catch (Exception e) {

			retObj.setCode(Status.INVALID.getIndex());
			retObj.setMessage("加载定时失败！");
			return retObj;
		}

		retObj.setCode(Status.EFFECTIVE.getIndex());
		return retObj;
	}
		@RequestMapping("addTask")
		@ResponseBody
		public RetObj addTask(String jobGroup,String jobName,String cronExpression,
				String beanClass,String springId,String methodName,
				String jobStatus,String description) {
			RetObj retObj = new RetObj();
			retObj.setCode(Status.INVALID.getIndex());
			ScheduleJob scheduleJob=new ScheduleJob();
			scheduleJob.setJobId(com.cn.util.IdGenerator.getId());
			scheduleJob.setJobGroup(jobGroup);
			scheduleJob.setJobName(jobName);
			scheduleJob.setCronExpression(cronExpression);
			scheduleJob.setBeanClass(beanClass);
			scheduleJob.setSpringId(springId);
			scheduleJob.setMethodName(methodName);
			scheduleJob.setJobStatus(jobStatus);
			scheduleJob.setDescription(description);

			try {
				taskService.addTask(scheduleJob);
			} catch (Exception e) {
				e.printStackTrace();
				retObj.setCode(Status.INVALID.getIndex());
				retObj.setMessage("保存失败，检查 name group 组合是否有重复！");
				return retObj;
			}

			retObj.setCode(Status.EFFECTIVE.getIndex());
			return retObj;
		}


	@RequestMapping("checkCron")
	@ResponseBody
	public RetObj checkCron(String cronExpression) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		} catch (Exception e) {
			retObj.setMessage("cron表达式有误，不能被解析！");
			return retObj;
		}

		retObj.setCode(Status.EFFECTIVE.getIndex());
		return retObj;
	}

	@RequestMapping("exist")
	@ResponseBody
	public RetObj exist(String jobGroup,String jobName) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setJobGroup(jobGroup);
		scheduleJob.setJobName(jobName);

		ScheduleJob job=taskService.getOneTask(scheduleJob);
		if(null==job) {
			retObj.setCode(Status.EFFECTIVE.getIndex());
		}else{
			retObj.setMessage("定时器重复");
		}
		return retObj;
	}

	@RequestMapping("checkBeanClass")
	@ResponseBody
	public RetObj checkBeanClass(String beanClass) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());
		Object obj = null;
		try {
			Class clazz = Class.forName(beanClass);
			obj = clazz.newInstance();
			if( obj==null){
				retObj.setMessage("未找类名");
			}else{
				retObj.setCode(Status.EFFECTIVE.getIndex());
			}

		} catch (Exception e) {
			retObj.setMessage("未找类名");

		}


		return retObj;
	}



	@RequestMapping("checkBeanName")
	@ResponseBody
	public RetObj checkBeanName(String beanName) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());
		Object obj = null;
		try {
			obj = SpringUtils.getBean(beanName);

			if( obj==null){
				retObj.setMessage("未找到实例名");
			}else{
				retObj.setCode(Status.EFFECTIVE.getIndex());
			}

		} catch (Exception e) {
			retObj.setMessage("未找到实例名");

		}

		return retObj;
	}

	@RequestMapping("checkMethod")
	@ResponseBody
	public RetObj checkBeanName(String beanClass,String beanName,String methodName) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());
		Object obj = null;

		try {
			if (StringUtils.isNotBlank(beanName)) {
				obj = SpringUtils.getBean(beanName);
			} else {
				Class clazz = Class.forName(beanClass);
				obj = clazz.newInstance();
			}
		} catch (Exception e) {
			// do nothing.........
		}
		if (obj == null) {
			retObj.setMessage("未找到目标类！");
			return retObj;
		} else {
			Class clazz = obj.getClass();
			Method method = null;
			try {
				method = clazz.getMethod(methodName, null);
			} catch (Exception e) {
				// do nothing.....
			}
			if (method == null) {
				retObj.setMessage("未找到目标方法！");
				return retObj;
			}else{
				retObj.setCode(Status.EFFECTIVE.getIndex());
			}
		}

		return retObj;
	}



	@RequestMapping("changeStatus")
	@ResponseBody
	public RetObj changeStatus(HttpServletRequest request, String jobId, String cmd) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());
		try {
			taskService.changeStatus(jobId, cmd);
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
			retObj.setMessage("任务状态改变失败！");
			return retObj;
		}
		retObj.setCode(Status.EFFECTIVE.getIndex());
		return retObj;
	}



	@RequestMapping("update")
	@ResponseBody
	public RetObj update(String jobId,String cronExpression, String jobStatus,String description) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());

		try {
			ScheduleJob scheduleJob = new ScheduleJob();
			scheduleJob.setJobId(jobId);
			ScheduleJob job = taskService.getOneTask(scheduleJob);

			scheduleJob.setDescription(description);
			scheduleJob.setCronExpression(cronExpression);
			job.setIsConcurrent(cronExpression);
			scheduleJob.setJobStatus(jobStatus);
			job.setJobStatus(jobStatus);
			scheduleJob.setUpdateTime(new Date());

			taskService.deleteJob(job );
			if(jobStatus.equals(String.valueOf(Status.EFFECTIVE.getIndex()))){
				taskService.addJob(job);
			}

			taskService.updateTask(scheduleJob);
		} catch (SchedulerException e) {
			retObj.setMessage("cron更新失败！");
			return retObj;
		}
		retObj.setCode(Status.EFFECTIVE.getIndex());
		return retObj;
	}

	@RequestMapping("deleteJob")
	@ResponseBody
	public RetObj deleteJob(String jobId) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobId(jobId);
		try {
			scheduleJob = taskService.getOneTask(scheduleJob);
			taskService.deleteJob(scheduleJob);
			retObj.setCode(Status.EFFECTIVE.getIndex());
		} catch (Exception e) {

			retObj.setCode(Status.INVALID.getIndex());
			retObj.setMessage("定时器删除失败！");
			return retObj;
		}

		retObj.setCode(Status.EFFECTIVE.getIndex());
		return retObj;
	}

	@RequestMapping("deleteTask")
	@ResponseBody
	public RetObj deleteTask(String jobId) {
		RetObj retObj = new RetObj();
		retObj.setCode(Status.INVALID.getIndex());
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobId(jobId);
		try {
			scheduleJob = taskService.getOneTask(scheduleJob);
			taskService.deleteTask(scheduleJob);
			retObj.setCode(Status.EFFECTIVE.getIndex());
		} catch (Exception e) {

			retObj.setCode(Status.INVALID.getIndex());
			retObj.setMessage("定时器删除失败！");
			return retObj;
		}

		retObj.setCode(Status.EFFECTIVE.getIndex());
		return retObj;
	}
}
