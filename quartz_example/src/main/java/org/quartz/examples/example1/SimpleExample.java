/* 
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */
 
package org.quartz.examples.example1;

import  org.quartz.DateBuilder;
import  org.quartz.JobBuilder;
import  org.quartz.TriggerBuilder;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * This Example will demonstrate how to start and shutdown the Quartz scheduler and how to schedule a job to run in
 * Quartz.
 * 
 * @author Bill Kratzer
 */
public class SimpleExample {

  public void run() throws Exception {
    Logger log = LoggerFactory.getLogger(SimpleExample.class);
    
    log.info("------- Initializing ----------------------");

    // First we must get a reference to a scheduler
    //Create an uninitialized StdSchedulerFactory.
    SchedulerFactory sf = new StdSchedulerFactory();
    //Returns a client-usable handle to a Scheduler. 
    Scheduler sched = sf.getScheduler();

    log.info("------- Initialization Complete -----------");

    //在给定时间的下一分钟执行,runTime是当前时间的下一分钟的时间
    Date runTime = DateBuilder.evenMinuteDate(new Date());

    log.info("------- Scheduling Job  -------------------");

    // define the job and tie it to our HelloJob class
    //定义这个工作为我们的HelloJob，JobBuilder.newJob使用工作者的class对象来创建一个JobBuilder
    //withIdentity(name, groupName)使用给定的名称和组JobKey识别任务明细.
    JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

    // Trigger the job to run on the next round minute
    //在下一分钟触发工作
    //withIdentity（name, group）
    Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

    // Tell quartz to schedule the job using our trigger
    sched.scheduleJob(job, trigger);
    
    log.info(job.getKey() + " will run at: " + runTime);

    // Start up the scheduler (nothing can actually run until the
    // scheduler has been started)
    sched.start();

    log.info("------- Started Scheduler -----------------");

    // wait long enough so that the scheduler as an opportunity to
    // run the job!
    log.info("------- Waiting 65 seconds... -------------");
    try {
      // wait 65 seconds to show job
      Thread.sleep(65L * 1000L);
      // executing...
    } catch (Exception e) {
      //
    }

    // shut down the scheduler
    log.info("------- Shutting Down ---------------------");
    sched.shutdown(true);
    log.info("------- Shutdown Complete -----------------");
  }

  public static void main(String[] args) throws Exception {

    SimpleExample example = new SimpleExample();
    example.run();

  }

}
