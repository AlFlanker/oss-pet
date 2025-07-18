package com.otr.authcore;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class AuthFilter extends OncePerRequestFilter {

    private final AuthProperties properties;
    private final AuthenticationManager authenticationManager;

    public AuthFilter(AuthProperties properties, AuthenticationManager authenticationManager) {
        this.properties = properties;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!Collections.list(request.getHeaderNames()).contains(properties.getHeader())) {
            filterChain.doFilter(request, response);
            return;
        }
        String headerValue = request.getHeader(properties.getHeader());
        HeaderAuthToken token = new HeaderAuthToken(headerValue);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        } catch (AuthenticationException ex) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }
}
