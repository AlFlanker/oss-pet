package com.otr.authcore;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

public class ExternalAuthToken extends AbstractAuthenticationToken {

    private final AuthInfo authInfo;

    public ExternalAuthToken(AuthInfo authInfo) {
        super(AuthorityUtils.createAuthorityList("ROLE_EXTERNAL"));
        this.authInfo = authInfo;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return authInfo.getUsername();
    }

    public AuthInfo getAuthInfo() {
        return authInfo;
    }
}
