package com.otr.authapi;

import feign.Retryer;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ImportAutoConfiguration(FeignAutoConfiguration.class)
public class AuthApiConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Retryer feignRetryer() {
        return new Retryer.Default();
    }
}
