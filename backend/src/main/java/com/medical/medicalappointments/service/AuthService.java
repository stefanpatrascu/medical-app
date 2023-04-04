package com.medical.medicalappointments.service;

import com.medical.medicalappointments.model.dto.LoginRequestDTO;
import com.medical.medicalappointments.security.config.JwtConfig;
import com.medical.medicalappointments.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private CsrfTokenRepository csrfTokenRepository;

    public ResponseUtil login(@Valid LoginRequestDTO loginRequest, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            final String jwtToken = jwtService.generateToken(authentication.getName());

            addJwtCookieToResponse(response, jwtToken);
            addXsrfCookieToResponse(response);

            return ResponseUtil.success("Session started");
        } catch (AuthenticationException ex) {
            return ResponseUtil.error(HttpStatus.UNAUTHORIZED, "Wrong email or password");
        }
    }

    private void addXsrfCookieToResponse(HttpServletResponse response) {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        CsrfToken csrfToken = csrfTokenRepository.generateToken(request);
        csrfTokenRepository.saveToken(csrfToken, request, response);

        final Cookie csrfCookie = new Cookie("XSRF-TOKEN", csrfToken.getToken());
        csrfCookie.setPath("/");
        response.addCookie(csrfCookie);
    }

    private void addJwtCookieToResponse(HttpServletResponse response, String jwtToken) {
        final Cookie jwtCookie = new Cookie("JWT-TOKEN", jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/"); // Set the path for the cookie
        jwtCookie.setMaxAge((int) jwtConfig.getExpirationTime() / 1000); // Set the cookie's max age to the same as the token's
        response.addCookie(jwtCookie);
    }
}
