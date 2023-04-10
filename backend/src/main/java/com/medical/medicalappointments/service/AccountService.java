package com.medical.medicalappointments.service;

import com.medical.medicalappointments.model.dto.ResponseEntityDTO;
import com.medical.medicalappointments.model.dto.UpdateUserRequestDTO;
import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.model.entity.UserInfo;
import com.medical.medicalappointments.model.repository.UserRepository;
import com.medical.medicalappointments.util.ResponseUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class AccountService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository userRepository;


    public void logout(HttpServletResponse response) {
        final Cookie logoutCookie = new Cookie("JWT-TOKEN", null);
        logoutCookie.setHttpOnly(true);
        logoutCookie.setSecure(true);
        logoutCookie.setPath("/");
        logoutCookie.setMaxAge(0); // Set the cookie to expire immediately

        response.addCookie(logoutCookie);

        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public User getCurrentUser(String email) {
        return customUserDetailsService.getUserDetails(email);
    }


    public ResponseEntity<ResponseEntityDTO> updateAccount(Authentication authentication, UpdateUserRequestDTO updatedUser) {
        final User currentUser = getCurrentUser(authentication.getName());
        if (updatedUser.getFirstName() != null) {
            currentUser.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            currentUser.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getEmail() != null) {
            currentUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            currentUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        UserInfo updatedUserInfo = currentUser.getUserInfo();
        if (updatedUserInfo == null) {
            updatedUserInfo = new UserInfo();
        }
        if (updatedUser.getCnp() != null) {
            updatedUserInfo.setCnp(updatedUser.getCnp());
        }
        if (updatedUser.getBirthDate() != null) {
            updatedUserInfo.setBirthDate(updatedUser.getBirthDate());
        }
        currentUser.setUserInfo(updatedUserInfo);

        userRepository.save(currentUser);

        if (updatedUser.getPassword() != null && updatedUser.getEmail() != null) {
            // Re-authenticate user with the new email and password
            UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(updatedUser.getEmail(), updatedUser.getPassword(), authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        } else if (updatedUser.getEmail() != null && updatedUser.getPassword() == null) {
            // Re-authenticate user with the new email
            UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(updatedUser.getEmail(), currentUser.getPassword(), authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        } else if (updatedUser.getEmail() == null && updatedUser.getPassword() != null) {
            // Re-authenticate user with the new password
            UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(currentUser.getEmail(), updatedUser.getPassword(), authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }

        return ResponseUtil.success("Account successfully updated");
    }

}
