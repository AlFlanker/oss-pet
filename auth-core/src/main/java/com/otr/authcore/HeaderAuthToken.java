package com.otr.authcore;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Authentication token carrying the raw header value.
 */
public class HeaderAuthToken extends AbstractAuthenticationToken {

    private final String header;

    public HeaderAuthToken(String header) {
        super(null);
        this.header = header;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return header;
    }
}
