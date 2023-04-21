IF
NOT EXISTS (SELECT 1 FROM users WHERE email = 'stefanpatrascu96+doctor@gmail.com')
BEGIN
INSERT INTO doctor_info (specialization_id, clinic_address)
    OUTPUT INSERTED.id
VALUES (1, '123 Heart Street');

DECLARE
@doctor_info_id BIGINT;
SET
@doctor_info_id = SCOPE_IDENTITY();
INSERT INTO users (first_name, last_name, password, email, role, avatar_file_name, doctor_info_id, patient_info_id)
VALUES ('Stefan', 'Doctor', '$2a$10$bq2FgYCCnRabeEjLc/GzXuq8FdYYXXate9V/rtecONR5JKNp6ruHW', 'stefanpatrascu96+doctor@gmail.com', 'DOCTOR', null, @doctor_info_id, null);
END

IF
NOT EXISTS (SELECT 1 FROM users WHERE email = 'stefanpatrascu96+patient@gmail.com')
BEGIN

INSERT INTO patient_info (address, phone_number)
    OUTPUT INSERTED.id
VALUES ('456 Wellness Avenue', '555-123-4567');

DECLARE
@patient_info_id BIGINT;
SET
@patient_info_id = SCOPE_IDENTITY();
INSERT INTO users (first_name, last_name, password, email, role, avatar_file_name, doctor_info_id, patient_info_id)
VALUES ('Stefan', 'Patient', '$2a$10$bq2FgYCCnRabeEjLc/GzXuq8FdYYXXate9V/rtecONR5JKNp6ruHW', 'stefanpatrascu96+patient@gmail.com', 'PATIENT', null, null,
        @patient_info_id);
END

IF
NOT EXISTS (SELECT 1 FROM users WHERE email = 'stefanpatrascu96+admin@gmail.com')
BEGIN

INSERT INTO users (first_name, last_name, password, email, role, avatar_file_name, doctor_info_id, patient_info_id)
VALUES ('Stefan', 'Admin', '$2a$10$bq2FgYCCnRabeEjLc/GzXuq8FdYYXXate9V/rtecONR5JKNp6ruHW', 'stefanpatrascu96+admin@gmail.com', 'ADMIN', null, null, null);
END
