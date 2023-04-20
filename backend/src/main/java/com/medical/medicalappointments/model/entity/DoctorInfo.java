package com.medical.medicalappointments.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "doctor_info")
public class DoctorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String specialty;

    @NotNull
    private String clinicAddress;

    @OneToOne(mappedBy = "doctorInfo")
    @JsonBackReference
    private User user;
}
