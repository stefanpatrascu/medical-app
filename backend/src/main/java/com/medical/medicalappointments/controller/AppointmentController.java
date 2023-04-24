package com.medical.medicalappointments.controller;
import com.medical.medicalappointments.model.dto.AddAppointmentRequestDTO;
import com.medical.medicalappointments.model.dto.ResponseEntityDTO;
import com.medical.medicalappointments.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @RequestMapping("/add")
    @PreAuthorize("hasAnyAuthority('PATIENT')")
    public ResponseEntity<ResponseEntityDTO> addAppointment(@RequestBody @Valid AddAppointmentRequestDTO request, Authentication authentication) {
        return this.appointmentService.addAppointment(request, authentication);
    }

    @GetMapping("/doctor/{id}/slots")
    public ResponseEntity<ResponseEntityDTO> getAvailableSlots(@PathVariable Long id) {
        return this.appointmentService.getAvailableSlots(id);
    }

}
