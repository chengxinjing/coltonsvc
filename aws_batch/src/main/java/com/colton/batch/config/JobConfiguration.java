package com.colton.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
    @Bean
    public Job userJob(JobBuilderFactory jobBuilderFactory, Step userStep){
        return jobBuilderFactory.get("The job is to load user from feed to gemfire").start(userStep).build();
    }
}
