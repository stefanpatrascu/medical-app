package com.medical.medicalappointments.service;


import com.medical.medicalappointments.model.dto.AddAppointmentRequestDTO;
import com.medical.medicalappointments.model.dto.AvailableSlotDTO;
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
import java.util.*;

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

        LocalDateTime startDateTime = request.getStartDateTime();
        LocalDateTime endDateTime = request.getEndDateTime();
        Date start = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant());
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorAndStartDateTimeBetweenOrEndDateTimeBetween(doctor.get(), LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault()), LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault()), LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault()), LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault()));


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

    public ResponseEntity<ResponseEntityDTO> getAvailableSlots(Long id) {
        Optional<User> doctor = userRepository.findByIdAndRoleAndIsActive(id, Role.DOCTOR, true);

        if (!doctor.isPresent()) {
            throw new IllegalArgumentException("Doctor not found");
        }

        List<Appointment> existingAppointments = appointmentRepository.findByDoctor(doctor.get());

        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0));
        List<LocalDateTime> availableSlots = new ArrayList<>();

        while (startDateTime.isBefore(endDateTime)) {
            LocalDateTime slotEndDateTime = startDateTime.plusMinutes(30);
            boolean slotAvailable = true;

            for (Appointment appointment : existingAppointments) {
                LocalDateTime appointmentStartDateTime = appointment.getStartDateTime();
                LocalDateTime appointmentEndDateTime = appointment.getEndDateTime();

                if ((startDateTime.isEqual(appointmentStartDateTime) || startDateTime.isAfter(appointmentStartDateTime))
                    && (startDateTime.isBefore(appointmentEndDateTime))) {
                    slotAvailable = false;
                    break;
                } else if (appointmentStartDateTime.isBefore(slotEndDateTime) && appointmentEndDateTime.isAfter(startDateTime)) {
                    slotAvailable = false;
                    break;
                }

            }

            if (slotAvailable) {
                availableSlots.add(startDateTime);
            }

            startDateTime = slotEndDateTime;
        }

        List<Map<String, String>> slots = new ArrayList<>();
        for (LocalDateTime slot : availableSlots) {
            Map<String, String> slotMap = new HashMap<>();
            slotMap.put("startDateTime", slot.toString());
            slotMap.put("endDateTime", slot.plusMinutes(30).toString());
            slots.add(slotMap);
        }

        return ResponseUtil.success("Available slots", slots);
    }
}
