package com.colton.batch.config;

import com.colton.batch.reader.UserReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ReaderConfiguration {
    @Bean
    @Lazy
    public ItemReader userInfoReader(){

        return new UserReader();
    }
}
