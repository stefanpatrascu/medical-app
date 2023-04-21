package com.medical.medicalappointments.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "doctor_specialization")
public class DoctorSpecialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_info_id")
    @JsonBackReference
    private DoctorInfo doctorInfo;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    @JsonUnwrapped
    private MedicalSpecialization specialization;
}
