package com.schedule.conf;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartsConf {
	
  @Bean
  public JobDetail quartzJobDetail() {
    return JobBuilder.newJob(BatchJob.class).storeDurably().build();
  }

  @Bean
  public Trigger jobTrigger() {
    SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
      .simpleSchedule()
      .withIntervalInSeconds(3)
      .withRepeatCount(5);
    return TriggerBuilder
      .newTrigger()
      .forJob(quartzJobDetail())
      .withSchedule(scheduleBuilder)
      .build();
  }
}