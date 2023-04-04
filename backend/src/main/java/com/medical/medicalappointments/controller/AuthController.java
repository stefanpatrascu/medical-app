package com.medical.medicalappointments.controller;
import com.medical.medicalappointments.model.dto.LoginRequestDTO;
import com.medical.medicalappointments.service.AuthService;
import com.medical.medicalappointments.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseUtil login(@Valid @RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) {
       return authService.login(loginRequest, response);
    }
}
