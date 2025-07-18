package com.otr.authapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalUserDto {
    private String username;
    private String organization;
    private List<String> documentTypes;
}
