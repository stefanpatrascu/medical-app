package com.medical.medicalappointments.controller;

import com.medical.medicalappointments.model.dto.ResponseEntityDTO;
import com.medical.medicalappointments.model.dto.UpdateUserRequestDTO;
import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.service.AccountService;
import com.medical.medicalappointments.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/logout")
    public ResponseEntity<ResponseEntityDTO> logout(HttpServletResponse response) {
        accountService.logout(response);
        return ResponseUtil.success("Session successfully closed");
    }


    @GetMapping("/my-account")
    public ResponseEntity<User> updateMyAccount(Authentication authentication) {
        final User user = accountService.getCurrentUser(authentication.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update-account")
    public ResponseEntity<ResponseEntityDTO> updateAccount(Authentication authentication, @RequestBody @Valid UpdateUserRequestDTO updatedUser) {
        final ResponseEntity<ResponseEntityDTO> updateAccount = accountService.updateAccount(authentication, updatedUser);
        return updateAccount;
    }

}

