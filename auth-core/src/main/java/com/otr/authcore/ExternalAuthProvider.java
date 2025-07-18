package com.otr.authcore;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Spring Security AuthenticationProvider that delegates to AuthService.
 */
public class ExternalAuthProvider implements AuthenticationProvider {

    private final AuthService authService;

    public ExternalAuthProvider(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String header = (String) authentication.getPrincipal();
        AuthInfo info = authService.authenticate(header);
        if (info == null) {
            throw new BadCredentialsException("Invalid authentication header");
        }
        AbstractAuthenticationToken result = new ExternalAuthToken(info);
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return HeaderAuthToken.class.isAssignableFrom(authentication);
    }
}
