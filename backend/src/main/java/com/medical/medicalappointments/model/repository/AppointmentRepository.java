package com.medical.medicalappointments.model.repository;

import com.medical.medicalappointments.model.entity.Appointment;
import com.medical.medicalappointments.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorAndStartDateTimeBetweenOrEndDateTimeBetween(User doctor, LocalDateTime startDateTime1, LocalDateTime endDateTime1, LocalDateTime startDateTime2, LocalDateTime endDateTime2);
    List<Appointment> findByDoctor(User doctor);
}
