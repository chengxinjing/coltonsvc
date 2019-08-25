package com.colton.batch.config;

import com.colton.batch.reader.UserReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReaderConfiguration {
    @Bean
    @StepScope
    public ItemReader userInfoReader(){

        return new UserReader();
    }
}
