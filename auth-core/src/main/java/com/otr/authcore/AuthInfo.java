package com.otr.authcore;

import java.util.List;

public class AuthInfo {
    private final String username;
    private final String organization;
    private final List<String> documentTypes;

    public AuthInfo(String username, String organization, List<String> documentTypes) {
        this.username = username;
        this.organization = organization;
        this.documentTypes = documentTypes;
    }

    public String getUsername() {
        return username;
    }

    public String getOrganization() {
        return organization;
    }

    public List<String> getDocumentTypes() {
        return documentTypes;
    }
}
