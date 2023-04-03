package com.medical.medicalappointments.service;

import com.medical.medicalappointments.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class AccountService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public void logout(HttpServletResponse response) {
        Cookie logoutCookie = new Cookie("JWT-TOKEN", null);
        logoutCookie.setHttpOnly(true);
        logoutCookie.setSecure(true);
        logoutCookie.setPath("/");
        logoutCookie.setMaxAge(0); // Set the cookie to expire immediately

        response.addCookie(logoutCookie);
    }

    public User getCurrentUser(String email) {
        return customUserDetailsService.getUserDetails(email);
    }
}
