package com.otr.osssecuritypet.filter;

import com.otr.osssecuritypet.config.PoibAuthenticationToken;
import com.otr.osssecuritypet.config.PoibProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

/**
 * Описание класса.
 *
 * @author alexraspopin
 * @since 17.07.2025
 */
@Slf4j
@RequiredArgsConstructor
public class PoibFilter extends OncePerRequestFilter {
    private final PoibProperties poibProperties;
    private final HttpSessionSecurityContextRepository repository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        if (isNotPoibHeader(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!Objects.equals(request.getHeader(poibProperties.getHeader()), "50eb9491-876b-480e-b71f-f835e37f9bd1")) 	{
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "text/plain;charset=UTF-8");
            response.getWriter().write("⛔⛔🤖🤖");
            return;
        }

        var auth = new PoibAuthenticationToken();
        var newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(auth);
        SecurityContextHolder.setContext(newContext);

//        SecurityContextRepository scr;
//        repository.saveContext(newContext, request, response);

        filterChain.doFilter(request, response);
    }

    private boolean isNotPoibHeader(HttpServletRequest request) {
        return !Collections.list(request.getHeaderNames()).contains(poibProperties.getHeader());
    }

    public static class PoibToken extends AbstractAuthenticationToken {
        public PoibToken() {
            super(AuthorityUtils.createAuthorityList("ROLE_poib"));
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return "POIB_user";
        }
    }
}
