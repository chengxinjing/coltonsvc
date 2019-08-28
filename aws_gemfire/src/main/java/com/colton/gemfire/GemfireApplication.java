package com.colton.gemfire;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
@SpringBootApplication
@EnableGemfireRepositories(basePackages = "com.colton.entity.repository")
@ImportResource("config/cacheServer.xml")
@EnablePdx
@CacheServerApplication(locators = "localhost[31431]",port = 8092,maxConnections = 200,maxThreads = 5,loadPollInterval = 5000,name = "MAIN")
//@EnableLocator(port = 31431)
public class GemfireApplication {
    public static void main(String[] args) {
        SpringApplication.run(GemfireApplication.class);
    }
}
