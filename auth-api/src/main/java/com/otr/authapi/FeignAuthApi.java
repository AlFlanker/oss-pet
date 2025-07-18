package com.otr.authapi;

import com.otr.authcore.AuthApi;
import com.otr.authcore.AuthInfo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FeignAuthApi implements AuthApi {
    private final AuthClient client;
    @Override
    public AuthInfo authenticate(String header) {
        ExternalUserDto dto = client.authenticate(header);
        return new AuthInfo(dto.getUsername(), dto.getOrganization(), dto.getDocumentTypes());
    }
}
