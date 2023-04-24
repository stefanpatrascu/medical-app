package com.medical.medicalappointments.model.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private User patient;

    @Column(name = "start_date_time")
    private Date startDateTime;

    @Column(name = "end_date_time")
    private Date endDateTime;

    @Column(length = 500)
    private String notes;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
