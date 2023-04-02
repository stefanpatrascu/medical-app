package com.medical.medicalappointments.component;

import org.springframework.beans.factory.annotation.Value;
import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.model.entity.UserInfo;
import com.medical.medicalappointments.model.enums.Role;
import com.medical.medicalappointments.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@DependsOn("entityManagerFactory")
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public DataInitializer(UserRepository patientRepository) {
        this.userRepository = patientRepository;
    }


    @Value("${default-admin-should-be-created}")
    private boolean defaultAdminShouldBeCreated;

    @Value("${default-admin-email}")
    private String defaultAdminEmail;

    @Value("${default-admin-password}")
    private String defaultAdminPassword;

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (userRepository.count() == 0 && defaultAdminShouldBeCreated) {
            User user = new User();
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setEmail(defaultAdminEmail);
            user.setPassword(passwordEncoder.encode(defaultAdminPassword));
            user.setRole(Role.ADMIN);

            UserInfo userInfo = new UserInfo();
            userInfo.setCnp("1234567890123");
            userInfo.setBirthDate(LocalDate.of(1996, 12, 27));

            user.setUserInfo(userInfo);

            userRepository.save(user);
        }
    }
}
