package org.quartz.examples.example16;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job {
	
	Logger logger = LoggerFactory.getLogger(HelloJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		String jobName = context.getTrigger().getJobKey().getName();
		
		String fireInstanceId = context.getFireInstanceId();
		
		Date date = context.getFireTime();
		
		String schedulerInstanceId = null;
		
		try {
			schedulerInstanceId = context.getScheduler().getSchedulerInstanceId();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
		logger.info("当前执行的任务的名字为：" + jobName);
		
		logger.info("触发的实例id为：" + fireInstanceId);
		
		logger.info("任务触发时间：" + date);
		
		logger.info("调度器实例id：" + schedulerInstanceId);
		
	}
	
	

}
