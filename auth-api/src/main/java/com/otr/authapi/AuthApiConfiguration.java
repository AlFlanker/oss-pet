package com.otr.authapi;

import feign.Retryer;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.otr.authcore.AuthApi;
import com.otr.authapi.FeignAuthApi;
import com.otr.authapi.AuthClient;

@Configuration
@EnableConfigurationProperties(AuthApiProperties.class)
@ImportAutoConfiguration(FeignAutoConfiguration.class)
public class AuthApiConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Retryer feignRetryer() {
        return new Retryer.Default();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthApi feignAuthApi(AuthClient client) {
        return new FeignAuthApi(client);
    }
}
