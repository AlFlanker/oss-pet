package com.otr.osssecuritypet.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Описание класса.
 *
 * @author alexraspopin
 * @since 17.07.2025
 */
@Data
@ConfigurationProperties(prefix = "poib")
public class PoibProperties {
    private String header;
}
