package com.medical.medicalappointments.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ResponseEntityDTO {
    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    private Date timestamp;

    public ResponseEntityDTO(int status, String message, String error) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = new Date();
    }
}
