package com.medical.medicalappointments.service;

import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.model.enums.Role;
import com.medical.medicalappointments.model.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DoctorService {
    private final UserRepository userRepository;

    public DoctorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> allDoctors() {
        return userRepository.findByRoleAndIsActive(Role.DOCTOR, true);
    }
}
