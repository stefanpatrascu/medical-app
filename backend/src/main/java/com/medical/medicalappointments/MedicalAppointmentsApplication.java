package com.medical.medicalappointments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MedicalAppointmentsApplication {

    private static String profile;
    private static String allowedOrigin;

    @Value("${profile}")
    public void setProfile(String profile) {
        MedicalAppointmentsApplication.profile = profile;
    }

    @Value("${cors.allowed-origins}")
    public void setAllowedOrigin(String allowedOrigin) {
        MedicalAppointmentsApplication.allowedOrigin = allowedOrigin;
    }

    public static void main(String[] args) {
        SpringApplication.run(MedicalAppointmentsApplication.class, args);
        System.out.println("---------------------------");
        System.out.println("Application Started");
        System.out.println("Application profile: " + profile);
        System.out.println("Allowed Origin: " + allowedOrigin);
        System.out.println("---------------------------");
    }


}
