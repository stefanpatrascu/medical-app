package com.medical.medicalappointments.model.repository;

import com.medical.medicalappointments.model.entity.Appointment;
import com.medical.medicalappointments.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorAndStartDateTimeBetweenOrEndDateTimeBetween(User doctor, Date startDateTime1, Date endDateTime1, Date startDateTime2, Date endDateTime2);


}
