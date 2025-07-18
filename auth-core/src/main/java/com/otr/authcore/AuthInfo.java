package com.otr.authcore;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AuthInfo {
    private final String username;
    private final String organization;
    private final List<String> documentTypes;
}
