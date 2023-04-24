package com.medical.medicalappointments.service;


import com.medical.medicalappointments.model.dto.AddAppointmentRequestDTO;
import com.medical.medicalappointments.model.dto.ResponseEntityDTO;
import com.medical.medicalappointments.model.entity.Appointment;
import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.model.enums.Role;
import com.medical.medicalappointments.model.repository.AppointmentRepository;
import com.medical.medicalappointments.model.repository.UserRepository;
import com.medical.medicalappointments.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository, EntityManager entityManager) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }
    public ResponseEntity<ResponseEntityDTO> addAppointment(AddAppointmentRequestDTO request, Authentication authentication) {

        final Optional<User> doctor = userRepository.findByIdAndRoleAndIsActive(request.getDoctorId(), Role.DOCTOR, true);
        final Optional<User> patient = userRepository.findByEmailAndIsActive(authentication.getName(), true);

        if (!doctor.isPresent()) {
            return ResponseUtil.error(HttpStatus.BAD_REQUEST, "Doctor not found");
        }

        if (!patient.isPresent()) {
            return ResponseUtil.error(HttpStatus.BAD_REQUEST, "Patient not found");
        }

        LocalDateTime startDateTime = LocalDateTime.ofInstant(request.getStartDateTime().toInstant(), ZoneId.systemDefault());
        LocalDateTime endDateTime = LocalDateTime.ofInstant(request.getEndDateTime().toInstant(), ZoneId.systemDefault());
        Date start = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant());
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorAndStartDateTimeBetweenOrEndDateTimeBetween(doctor.get(), start, end, start, end);

        if (!existingAppointments.isEmpty()) {
            return ResponseUtil.error(HttpStatus.BAD_REQUEST, "Appointment time slot is not available");
        }


        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor.get());
        appointment.setPatient(patient.get());
        appointment.setNotes(request.getNotes());
        appointment.setStartDateTime(request.getStartDateTime());
        appointment.setEndDateTime(request.getEndDateTime());


        appointmentRepository.save(appointment);


        return ResponseUtil.success("Appointment created", null);

    }

}
