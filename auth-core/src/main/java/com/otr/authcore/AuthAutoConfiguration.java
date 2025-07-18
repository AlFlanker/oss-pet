package com.otr.authcore;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableConfigurationProperties(AuthProperties.class)
@ImportAutoConfiguration(com.otr.authapi.AuthApiConfiguration.class)
public class AuthAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ExternalAuthProvider externalAuthProvider(AuthService service) {
        return new ExternalAuthProvider(service);
    }

    @Bean(name = "externalAuthManager")
    @ConditionalOnMissingBean(name = "externalAuthManager")
    public AuthenticationManager authenticationManager(ExternalAuthProvider provider) {
        return new ProviderManager(provider);
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthFilter authFilter(AuthProperties properties, AuthenticationManager externalAuthManager) {
        return new AuthFilter(properties, externalAuthManager);
    }

    @Bean
    @ConditionalOnMissingBean(name = "externalAuthFilterChain")
    public SecurityFilterChain externalAuthFilterChain(HttpSecurity http, AuthFilter authFilter, ExternalAuthProvider provider) throws Exception {
        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(provider);
        return http.build();
    }
}
