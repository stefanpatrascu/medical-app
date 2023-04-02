package com.medical.medicalappointments.controller;
import com.medical.medicalappointments.model.dto.LoginRequestDTO;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
        String jwt = jwtService.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(jwt);
    }
}
