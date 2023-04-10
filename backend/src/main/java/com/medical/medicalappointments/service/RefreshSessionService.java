package com.medical.medicalappointments.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
public class RefreshSessionService {


    private SessionRegistry sessionRegistry;

    @Autowired
    public RefreshSessionService(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public void refreshSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SecurityContext securityContext = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        Authentication authentication = securityContext.getAuthentication();

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());

        securityContext.setAuthentication(newAuthentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

        sessionRegistry.registerNewSession(session.getId(), newAuthentication.getPrincipal());
    }
}
