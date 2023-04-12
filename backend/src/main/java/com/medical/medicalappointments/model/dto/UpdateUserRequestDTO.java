package com.medical.medicalappointments.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Email;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateUserRequestDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters long")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters long")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "CNP is required")
    @Pattern(regexp = "^[1-8]\\d{12}$", message = "CNP must be a valid 13-digit number")
    private String cnp;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate = LocalDate.now();

    @Size(min = 4, max = 64, message = "Password must be between 4 and 64 characters long")
    private String password;

    @Size(min = 4, max = 64, message = "Password must be between 4 and 64 characters long")
    private String confirmPassword;
}
