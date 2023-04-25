package com.medical.medicalappointments.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class AddAppointmentRequestDTO {
    @NotNull
    private Long doctorId;

    @Size(max = 500, message = "Max 500 characters are allowed")
    private String notes;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    @AssertTrue(message = "End date time must be after start date time")
    private boolean isValid() {
        if (startDateTime == null || endDateTime == null) {
            return true;
        }
        return startDateTime.isBefore(endDateTime);
    }


    @AssertTrue(message = "Appointment duration must not exceed 2 hours")
    private boolean isValidDuration() {
        if (startDateTime == null || endDateTime == null) {
            return true;
        }
        Duration duration = Duration.between(startDateTime, endDateTime);
        return duration.toHours() <= 2;
    }

}
