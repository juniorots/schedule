package com.schedule.conf;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConf {
	
  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job job() {
    return this.jobBuilderFactory.get("jobQuartz")
    		.incrementer(new RunIdIncrementer())
    		.start(step1()).build();
  }

  @Bean
  public Step step1() {
    return this.stepBuilderFactory.get("step1")
    		.tasklet((stepContribution, chunkContext) -> {
    			System.out.println("Executando step1");
    			return RepeatStatus.FINISHED;
    }).build();
  }
}
