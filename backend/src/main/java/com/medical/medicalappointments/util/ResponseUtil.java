package com.medical.medicalappointments.util;
import org.springframework.http.HttpStatus;

public class ResponseUtil {
    private int code;
    private String message;

    public ResponseUtil(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseUtil success(String message) {
        return new ResponseUtil(HttpStatus.OK.value(), message);
    }

    public static ResponseUtil error(HttpStatus status, String message) {
        return new ResponseUtil(status.value(), message);
    }

    public static ResponseUtil error(int code, String message) {
        return new ResponseUtil(code, message);
    }
}

