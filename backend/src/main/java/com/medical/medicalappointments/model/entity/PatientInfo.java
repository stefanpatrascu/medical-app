package com.medical.medicalappointments.model.entity;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "patient_info")
public class PatientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String address;

    @NotNull
    private String phoneNumber;
}
