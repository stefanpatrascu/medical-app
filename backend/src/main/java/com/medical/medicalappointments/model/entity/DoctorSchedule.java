package com.medical.medicalappointments.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "doctor_schedule")
public class DoctorSchedule {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monday_start", columnDefinition = "TIME DEFAULT 0")
    private LocalTime mondayStart;

    @Column(name = "monday_end", columnDefinition = "TIME DEFAULT 0")
    private LocalTime mondayEnd;

    @Column(name = "tuesday_start", columnDefinition = "TIME DEFAULT 0")
    private LocalTime tuesdayStart;

    @Column(name = "tuesday_end", columnDefinition = "TIME DEFAULT 0")
    private LocalTime tuesdayEnd;

    @Column(name = "wednesday_start", columnDefinition = "TIME DEFAULT 0")
    private LocalTime wednesdayStart;

    @Column(name = "wednesday_end", columnDefinition = "TIME DEFAULT 0")
    private LocalTime wednesdayEnd;

    @Column(name = "thursday_start", columnDefinition = "TIME DEFAULT 0")
    private LocalTime thursdayStart;

    @Column(name = "thursday_end", columnDefinition = "TIME DEFAULT 0")
    private LocalTime thursdayEnd;

    @Column(name = "friday_start", columnDefinition = "TIME DEFAULT 0")
    private LocalTime fridayStart;

    @Column(name = "friday_end", columnDefinition = "TIME DEFAULT 0")
    private LocalTime fridayEnd;

    @Column(name = "saturday_start", columnDefinition = "TIME DEFAULT 0")
    private LocalTime saturdayStart;

    @Column(name = "saturday_end", columnDefinition = "TIME DEFAULT 0")
    private LocalTime saturdayEnd;

    @Column(name = "sunday_start", columnDefinition = "TIME DEFAULT 0")
    private LocalTime sundayStart;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
