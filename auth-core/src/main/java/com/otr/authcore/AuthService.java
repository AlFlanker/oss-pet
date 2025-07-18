package com.otr.authcore;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class AuthService {

    private final AuthApi client;
    private final Cache<String, AuthInfo> cache;

    public AuthService(AuthApi client, AuthProperties properties) {
        this.client = client;
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(properties.getCacheTtl())
                .build();
    }

    public AuthInfo authenticate(String headerValue) {
        return cache.get(headerValue, this::load);
    }

    private AuthInfo load(String header) {
        return client.authenticate(header);
    }
}
