package com.medical.medicalappointments.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "doctor_info")
public class DoctorInfo {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String clinicAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private DoctorSchedule doctorSchedule;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "doctor_specialization",
        joinColumns = @JoinColumn(name = "doctor_info_id"),
        inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private List<MedicalSpecialization> specializations;
}
