package com.otr.authcore;

/**
 * Interface for external authentication API.
 */
public interface AuthApi {
    /**
     * Authenticate user using provided header value.
     * @param header header value
     * @return authenticated user info or null if authentication failed
     */
    AuthInfo authenticate(String header);
}
