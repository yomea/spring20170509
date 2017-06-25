package org.quartz.examples.example16;

import java.util.concurrent.TimeUnit;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleExample {
	
	private void run() {
		
		Scheduler scheduler = null;
		
		try {
			
			//调度器工厂
			SchedulerFactory sf = new StdSchedulerFactory("quartz.properties");
			
			//获得一个调度器
			scheduler = sf.getScheduler();
			//定义一个工作明细
			JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
			//定义一个触发器
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trgger1", "group1").withSchedule(SimpleScheduleBuilder.simpleSchedule()
		              .withIntervalInMilliseconds(1000)
		              .repeatForever()).build();
			
			//将任务明细交给触发器
			scheduler.scheduleJob(jobDetail, trigger);
			//开始调度
			scheduler.start();
			
		} catch (SchedulerException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				TimeUnit.SECONDS.sleep(10);
				scheduler.shutdown();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public static void main(String[] args) {
		
		SimpleExample se = new SimpleExample();
		
		se.run();
		
		
		
	}
	

}
