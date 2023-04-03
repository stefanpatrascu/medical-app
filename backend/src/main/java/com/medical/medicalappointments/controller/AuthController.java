package com.medical.medicalappointments.controller;
import com.medical.medicalappointments.annotations.RoleRequired;
import com.medical.medicalappointments.config.JwtConfig;
import com.medical.medicalappointments.model.dto.LoginRequestDTO;
import com.medical.medicalappointments.model.enums.Role;
import com.medical.medicalappointments.service.CustomUserDetailsService;
import com.medical.medicalappointments.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtConfig jwtConfig;

    @GetMapping("/test-api")
    @RoleRequired({Role.ADMIN, Role.PATIENT})
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Test", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie logoutCookie = new Cookie("JWT-TOKEN", null);
        logoutCookie.setHttpOnly(true);
        logoutCookie.setSecure(true);
        logoutCookie.setPath("/");
        logoutCookie.setMaxAge(0); // Set the cookie to expire immediately

        response.addCookie(logoutCookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
        String jwtToken = jwtService.generateToken(userDetails.getUsername());

        // Create HttpOnly cookie
        Cookie jwtCookie = new Cookie("JWT-TOKEN", jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/"); // Set the path for the cookie
        jwtCookie.setMaxAge((int) jwtConfig.getExpirationTime() / 1000); // Set the cookie's max age to the same as the token's

        // Add the cookie to the response
        response.addCookie(jwtCookie);

        return ResponseEntity.ok("Login successful");
    }
}
