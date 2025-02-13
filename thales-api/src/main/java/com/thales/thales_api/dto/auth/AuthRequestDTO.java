package com.thales.thales_api.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AuthRequestDTO {
    private String username;
    private String password;
}
