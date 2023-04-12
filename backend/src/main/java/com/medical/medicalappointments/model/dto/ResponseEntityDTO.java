package com.medical.medicalappointments.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ResponseEntityDTO {
    private int status;
    private String message;
    private String error;
    private Date timestamp;

    public ResponseEntityDTO(int status, String message, String error) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = new Date();
    }
}
