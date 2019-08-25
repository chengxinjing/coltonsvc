package com.colton.batch.config;

import com.colton.batch.writer.UserInfoWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterConfiguration {

    @Bean
    public ItemWriter userInfoWriter(){
        return new UserInfoWriter();
    }
}
