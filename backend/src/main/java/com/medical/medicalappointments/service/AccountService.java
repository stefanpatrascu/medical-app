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

    @Autowired
    private JwtService jwtService;


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


    public ResponseEntity<ResponseEntityDTO> updateAccount(Claims claims, UpdateUserRequestDTO updatedUser, HttpServletResponse response) {
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final User currentUser = getCurrentUser(claims.get("email").toString());
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        UserInfo updatedUserInfo = currentUser.getUserInfo();
        if (updatedUserInfo != null) {
            UserInfo currentUserInfo = currentUser.getUserInfo();
            if (currentUserInfo == null) {
                currentUserInfo = new UserInfo();
            }
            currentUserInfo.setCnp(updatedUserInfo.getCnp());
            currentUserInfo.setBirthDate(updatedUserInfo.getBirthDate());
        }

        userRepository.save(currentUser);

        final String newJwtToken = jwtService.generateToken(currentUser.getEmail());
        jwtService.addJwtCookieToResponse(response, newJwtToken);

        return ResponseUtil.success("Account successfully updated");
    }
}
