package com.otr.authapi;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@ConfigurationProperties(prefix = "auth.api")
@Data
public class AuthApiProperties {
    /** Base URL of external auth service */
    private String url;
}
