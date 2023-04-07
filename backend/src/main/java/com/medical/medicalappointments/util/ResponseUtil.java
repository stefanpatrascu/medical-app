package com.medical.medicalappointments.util;
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

    public static ResponseEntity<ResponseEntityDTO> success(String message) {
        ResponseEntityDTO errorResponse = new ResponseEntityDTO(HttpStatus.OK.value(), message);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseEntityDTO> error(HttpStatus status, String message) {
        ResponseEntityDTO errorResponse = new ResponseEntityDTO(status.value(), message);
        return new ResponseEntity<>(errorResponse, status);
    }

}

