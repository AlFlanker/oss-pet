package com.otr.authapi;

import java.util.List;

public class ExternalUserDto {
    private String username;
    private String organization;
    private List<String> documentTypes;

    public ExternalUserDto() {
    }

    public ExternalUserDto(String username, String organization, List<String> documentTypes) {
        this.username = username;
        this.organization = organization;
        this.documentTypes = documentTypes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<String> getDocumentTypes() {
        return documentTypes;
    }

    public void setDocumentTypes(List<String> documentTypes) {
        this.documentTypes = documentTypes;
    }
}
