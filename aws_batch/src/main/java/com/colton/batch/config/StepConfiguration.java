package com.colton.batch.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class StepConfiguration {
    @Bean
    public Step userStep(StepBuilderFactory stepBuilderFactory, ItemReader userInfoReader, ItemWriter userInfoWriter){
        return stepBuilderFactory.get("step to load feed").chunk(10).reader(userInfoReader).writer(userInfoWriter).taskExecutor(taskExecutor()).build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
       return new TaskExecutorBuilder().corePoolSize(2).maxPoolSize(4).threadNamePrefix("user_step_").build();
    }
}
