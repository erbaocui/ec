package com.cn.job;

import com.cn.dao.IProcedureDao;
import com.cn.dao.IVersionDao;
import com.cn.model.Version;
import com.cn.service.IEsService;

import com.cn.service.IVersionService;
import com.cn.util.IdGenerator;
import com.cn.vo.JobLog;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("jobService")
public class JobService implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        JobService.applicationContext = applicationContext;
    }



    public void executeStatisticsMonth() {
        IProcedureDao procedureDao= (IProcedureDao)applicationContext.getBean("procedureDao");
        IEsService esService= (IEsService)applicationContext.getBean("esService");

        JobLog jobLog=new JobLog();
        jobLog.setId(IdGenerator.getId());
        jobLog.setBean(this.getClass().getName());
        jobLog.setMethod(Thread.currentThread().getStackTrace()[1].getMethodName());
        try {

            jobLog.setStartTime(new Date());
            procedureDao.runStatisticsMonth();
            jobLog.setFinishTime(new Date());
            jobLog.setStatus(0);
        } catch (Exception e) {
            jobLog.setFinishTime(new Date());
            String fullStackTrace = ExceptionUtils.getStackTrace(e);
            jobLog.setErrorStack( fullStackTrace);
            jobLog.setStatus(1);
        }finally {
            jobLog.setExecuteTime(String.valueOf( jobLog.getFinishTime().getTime() - jobLog.getStartTime().getTime()));
            esService.addJobLog(jobLog);
        }
    }
}
