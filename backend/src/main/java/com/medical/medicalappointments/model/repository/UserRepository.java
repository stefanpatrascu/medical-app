package com.medical.medicalappointments.model.repository;

import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndIsActive(String email, Boolean isActive);
    Optional<User> findByRoleAndIsActive(Role role, Boolean isActive);
    Optional<User> findByIdAndRoleAndIsActive(Long id, Role role, Boolean isActive);
}
