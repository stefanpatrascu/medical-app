package com.medical.medicalappointments.controller;

import com.medical.medicalappointments.annotations.AccessToken;
import com.medical.medicalappointments.annotations.RoleRequired;
import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.model.enums.Role;
import com.medical.medicalappointments.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/current-user")
    @RoleRequired({Role.ADMIN, Role.PATIENT})
    public ResponseEntity<User> currentUser(@AccessToken Claims claims) {
        final User user = customUserDetailsService.getUserDetails(claims.get("email").toString());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
