package com.otr.authcore;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

import java.time.Duration;

@ConfigurationProperties(prefix = "auth")
@Data
public class AuthProperties {
    /** Header name */
    private String header = "X-Auth";
    /** Cache TTL */
    private Duration cacheTtl = Duration.ofMinutes(10);
}
