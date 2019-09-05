package com.colton.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
    @Bean
    @Scope("prototype")
    @Lazy
    public Job userJob(JobBuilderFactory jobBuilderFactory, Step userStep){
        return jobBuilderFactory.get("The job is to load user from feed to gemfire").start(userStep).build();
    }
}
