package com.medical.medicalappointments.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.medical.medicalappointments.model.dto.ResponseEntityDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseUtil {
    private int code;
    private String message;


    public ResponseUtil(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseEntity<ResponseEntityDTO> success(String message, Object data) {
        ResponseEntityDTO errorResponse = new ResponseEntityDTO(HttpStatus.OK.value(), message, null, data);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseEntityDTO> error(HttpStatus status, String message) {
        ResponseEntityDTO errorResponse = new ResponseEntityDTO(status.value(), null, message, null);
        return new ResponseEntity<>(errorResponse, status);
    }

}

