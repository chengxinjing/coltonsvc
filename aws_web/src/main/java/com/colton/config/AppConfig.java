package com.colton.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.PropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.colton.exception.NotFoundCredentialsProviderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.util.ObjectUtils;

@Slf4j
@Configuration
public class AppConfig {
    @Value("${aws.credential}")
    String credentials;

    @Value("${aws.region:ap-northeast-1}")
    String region;

    @Bean
    @Lazy
    @Scope("prototype")
    public Regions regions() {
        return Regions.fromName(region);
    }

    @Bean
    @Lazy
    public RegionsManager regionsManager() {
        return () -> regions();
    }

    @Bean
    @Lazy
    public AWSCredentialsProvider propsProvider() {
        return new PropertiesFileCredentialsProvider(credentials);
    }

    @Bean
    @Lazy
    public AWSCredentialsProvider envProvider() {
        return new EnvironmentVariableCredentialsProvider();
    }

    @Bean
    @Lazy
    public AmazonS3 amazonS3(AWSCredentialsProvider propsProvider, AWSCredentialsProvider envProvider, RegionsManager regionsManager) {
        return AmazonS3ClientBuilder.standard().withCredentials(autoSelectedProvider(propsProvider, envProvider)).withRegion(regionsManager.createRegion()).build();
    }

    private AWSCredentialsProvider autoSelectedProvider(AWSCredentialsProvider propsProvider, AWSCredentialsProvider envProvider) {
        if (isCanUseProvider(propsProvider)) {
            return propsProvider;
        } else if (isCanUseProvider(envProvider)) {
            return envProvider;
        } else {
            new NotFoundCredentialsProviderException("Please provide the  credentials provider at least one");
        }
        return null;
    }

    private boolean isCanUseProvider(AWSCredentialsProvider provider) {
        try {
            if (ObjectUtils.isEmpty(provider.getCredentials())) {
                return false;
            }
        } catch (Exception ex) {
            if (log.isDebugEnabled())
             log.warn(ex.getMessage(), ex);
            return false;
        }
        return true;
    }
}
