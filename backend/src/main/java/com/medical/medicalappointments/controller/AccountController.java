package com.medical.medicalappointments.controller;

import com.medical.medicalappointments.security.AccessToken;
import com.medical.medicalappointments.security.RoleRequired;
import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.model.enums.Role;
import com.medical.medicalappointments.service.AccountService;
import com.medical.medicalappointments.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/logout")
    @RoleRequired({Role.ADMIN, Role.PATIENT, Role.DOCTOR})
    public ResponseEntity<?> logout(HttpServletResponse response) {
        accountService.logout(response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/my-account")
    @RoleRequired({Role.ADMIN, Role.PATIENT, Role.DOCTOR})
    public ResponseEntity<User> currentUser(@AccessToken Claims claims) {
        User user = accountService.getCurrentUser(claims.get("email").toString());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

