package com.medical.medicalappointments.controller;
import com.medical.medicalappointments.model.dto.LoginRequestDTO;
import com.medical.medicalappointments.model.dto.ResponseEntityDTO;
import com.medical.medicalappointments.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseEntityDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) {
       return authService.login(loginRequest, response);
    }
}
