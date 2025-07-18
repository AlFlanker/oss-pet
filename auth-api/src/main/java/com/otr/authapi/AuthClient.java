package com.otr.authapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ext-auth", url = "${auth.api.url}")
public interface AuthClient {

    @GetMapping("/auth")
    ExternalUserDto authenticate(@RequestHeader("user") String user);
}
