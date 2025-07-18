package com.otr.osssecuritypet.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * Описание класса.
 *
 * @author alexraspopin
 * @since 18.07.2025
 */
public class PoibAuthenticationToken extends AbstractAuthenticationToken {
    public PoibAuthenticationToken() {
        super(AuthorityUtils.createAuthorityList("ROLE_POIB"));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return "USER_FROM_POIB";
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        throw new RuntimeException("Can’t touch this!");
    }
}
