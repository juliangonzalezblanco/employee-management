package com.thales.thales_api.controller;

import com.thales.thales_api.dto.auth.AuthRequestDTO;
import com.thales.thales_api.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class AuthControllerTest {

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController authController;

    private String validUsername = "user";
    private String validPassword = "pass";

    @BeforeEach
    public void setUp() {
        authController.validUsername = validUsername;
        authController.validPassword = validPassword;
    }

    @Test
    public void testLoginWithValidCredentials() {
        AuthRequestDTO request = new AuthRequestDTO(validUsername, validPassword);
        String expectedToken = "mockToken";
        when(jwtUtil.getJWTToken(validUsername)).thenReturn(expectedToken);

        ResponseEntity<?> response = authController.login(request);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof Map);
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals(expectedToken, responseBody.get("token"));
        verify(jwtUtil).getJWTToken(validUsername);
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        AuthRequestDTO request = new AuthRequestDTO("invalidUser", "invalidPass");

        ResponseEntity<?> response = authController.login(request);

        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid credentials", response.getBody());
    }

    @Test
    public void testLoginWithInvalidUsername() {
        AuthRequestDTO request = new AuthRequestDTO("invalidUser", validPassword);

        ResponseEntity<?> response = authController.login(request);

        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid credentials", response.getBody());
    }

    @Test
    public void testLoginWithInvalidPassword() {
        AuthRequestDTO request = new AuthRequestDTO(validUsername, "invalidPass");

        ResponseEntity<?> response = authController.login(request);

        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid credentials", response.getBody());
    }

}
