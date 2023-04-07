package com.medical.medicalappointments.model.dto;

public class ResponseEntityDTO {
    private int status;
    private String message;

    public ResponseEntityDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
