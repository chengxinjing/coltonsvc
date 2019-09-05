package com.colton.batch.config;

import com.colton.batch.writer.UserInfoWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class WriterConfiguration {

    @Bean
    @Lazy
    public ItemWriter userInfoWriter(){
        return new UserInfoWriter();
    }
}
