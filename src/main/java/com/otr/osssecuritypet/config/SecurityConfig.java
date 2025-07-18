package com.otr.osssecuritypet.config;

import com.otr.osssecuritypet.filter.PoibFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Описание класса.
 *
 * @author alexraspopin
 * @since 17.07.2025
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(PoibProperties.class)
class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, PoibFilter poibFilter) throws Exception {
        http
                .authorizeHttpRequests(
                        authorizeHttp -> {
                            authorizeHttp.requestMatchers("/").permitAll();
                            authorizeHttp.requestMatchers("/favicon.svg").permitAll();
                            authorizeHttp.requestMatchers("/css/*").permitAll();
                            authorizeHttp.requestMatchers("/error").permitAll();
                            authorizeHttp.anyRequest().authenticated();
                        }
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .addFilterBefore(poibFilter, AuthorizationFilter.class)
                .authenticationProvider(new DanielAuthenticationProvider());


        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

//    @Bean
//    public SecurityContextRepository httpSessionRequestRepository() {
//        return new HttpSessionRequestRepository()
//    }

    @Bean
    HttpSessionSecurityContextRepository httpSessionSecurityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }
    @Bean
    PoibFilter poibFilter(PoibProperties poibProperties,
                          HttpSessionSecurityContextRepository httpSessionSecurityContextRepository) {
        return new PoibFilter(poibProperties, httpSessionSecurityContextRepository);
    }
}
