package com.medical.medicalappointments.service;

import com.medical.medicalappointments.model.dto.LoginRequestDTO;
import com.medical.medicalappointments.model.dto.ResponseEntityDTO;
import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import javax.validation.Valid;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final CsrfTokenRepository csrfTokenRepository;
    private final AccountService accountService;

    AuthService(AuthenticationManager authenticationManager, CsrfTokenRepository csrfTokenRepository, AccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.csrfTokenRepository = csrfTokenRepository;
        this.accountService = accountService;
    }

    public ResponseEntity<ResponseEntityDTO> login(@Valid LoginRequestDTO loginRequest, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            addXsrfCookieToResponse(response);

            final User user = accountService.getCurrentUser(authentication.getName());

            return ResponseUtil.success("Session started", user);
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

}
