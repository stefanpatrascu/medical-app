package com.medical.medicalappointments.service;

import com.medical.medicalappointments.model.dto.LoginRequestDTO;
import com.medical.medicalappointments.security.config.JwtConfig;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtConfig jwtConfig;

    public ResponseEntity<String> login(@Valid LoginRequestDTO loginRequest, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = jwtService.generateToken(authentication.getName());

            addJwtCookieToResponse(response, jwtToken);

            return ResponseEntity.ok("Login successful");
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private void addJwtCookieToResponse(HttpServletResponse response, String jwtToken) {
        Cookie jwtCookie = new Cookie("JWT-TOKEN", jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/"); // Set the path for the cookie
        jwtCookie.setMaxAge((int) jwtConfig.getExpirationTime() / 1000); // Set the cookie's max age to the same as the token's
        response.addCookie(jwtCookie);
    }
}
