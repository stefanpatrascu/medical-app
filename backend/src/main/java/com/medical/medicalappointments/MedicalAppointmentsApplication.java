package com.medical.medicalappointments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MedicalAppointmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalAppointmentsApplication.class, args);
	}

}
