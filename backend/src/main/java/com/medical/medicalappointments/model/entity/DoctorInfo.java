package com.medical.medicalappointments.model.entity;
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

    @OneToOne
    @JoinColumn(name = "specialization_id", referencedColumnName = "id")
    private MedicalSpecialization medicalSpecialization;

    @NotNull
    private String clinicAddress;
}
