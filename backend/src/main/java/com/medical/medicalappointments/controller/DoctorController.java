package com.medical.medicalappointments.controller;
import com.medical.medicalappointments.model.dto.ResponseDoctorList;
import com.medical.medicalappointments.model.entity.DoctorSpecialization;
import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/all")
    public List<ResponseDoctorList> getAllDoctors() {
        Optional<User> allDoctors = doctorService.allDoctors();
        return allDoctors.stream()
            .map(user -> ResponseDoctorList.builder()
                .id(user.getId())
                .specializations(user.getDoctorInfo().getSpecializations())
                .doctorName(user.getUserInfo().getFirstName() + " " + user.getUserInfo().getLastName())
                .build())
            .collect(Collectors.toList());
    }

}
