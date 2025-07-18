package com.otr.authcore;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "auth")
public class AuthProperties {
    /** Header name */
    private String header = "X-Auth";
    /** Cache TTL */
    private Duration cacheTtl = Duration.ofMinutes(10);

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Duration getCacheTtl() {
        return cacheTtl;
    }

    public void setCacheTtl(Duration cacheTtl) {
        this.cacheTtl = cacheTtl;
    }
}
