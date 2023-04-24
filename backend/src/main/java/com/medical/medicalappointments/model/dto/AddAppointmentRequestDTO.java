package com.medical.medicalappointments.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Data
public class AddAppointmentRequestDTO {
    @NotNull
    private Long doctorId;

    @Size(max = 500, message = "Max 500 characters are allowed")
    private String notes;

    @NotNull
    private Date startDateTime;

    @NotNull
    private Date endDateTime;
}
