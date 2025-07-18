package com.otr.osssecuritypet.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Описание класса.
 *
 * @author alexraspopin
 * @since 17.07.2025
 */
@Controller
public class MainController {

    @GetMapping
    public String getIndex(Authentication authentication) {
        return "index";
    }

    @GetMapping("/secret")
    public String getSecret(Authentication authentication) {
        return "private";
    }
}
