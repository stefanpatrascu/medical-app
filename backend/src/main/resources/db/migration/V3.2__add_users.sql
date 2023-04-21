-- Common variables
DECLARE @password NVARCHAR(MAX) = '$2a$10$bq2FgYCCnRabeEjLc/GzXuq8FdYYXXate9V/rtecONR5JKNp6ruHW';

-- Insert doctor if not already in the users table
IF NOT EXISTS (SELECT 1 FROM users WHERE email = 'stefanpatrascu96+doctor@gmail.com')
    BEGIN
        INSERT INTO doctor_info (clinic_address)
        OUTPUT INSERTED.id
        VALUES ('123 Heart Street');

        DECLARE @doctor_info_id BIGINT;
        SET @doctor_info_id = SCOPE_IDENTITY();

        INSERT INTO users (first_name, last_name, password, email, role, avatar_file_name, doctor_info_id, patient_info_id)
        VALUES ('Stefan', 'Doctor', @password, 'stefanpatrascu96+doctor@gmail.com', 'DOCTOR', null, @doctor_info_id, null);

        INSERT INTO doctor_specialization (doctor_info_id, specialization_id)
        VALUES (@doctor_info_id, (SELECT TOP 1 MIN(id) FROM medical_specializations ));

        INSERT INTO doctor_specialization (doctor_info_id, specialization_id)
        VALUES (@doctor_info_id, (SELECT TOP 1 MAX(id) FROM medical_specializations ));
    END

-- Insert patient if not already in the users table
IF NOT EXISTS (SELECT 1 FROM users WHERE email = 'stefanpatrascu96+patient@gmail.com')
    BEGIN
        INSERT INTO patient_info (address, phone_number)
        OUTPUT INSERTED.id
        VALUES ('456 Wellness Avenue', '555-123-4567');

        DECLARE @patient_info_id BIGINT;
        SET @patient_info_id = SCOPE_IDENTITY();

        INSERT INTO users (first_name, last_name, password, email, role, avatar_file_name, doctor_info_id, patient_info_id)
        VALUES ('Stefan', 'Patient', @password, 'stefanpatrascu96+patient@gmail.com', 'PATIENT', null, null, @patient_info_id);
    END

-- Insert admin if not already in the users table
IF NOT EXISTS (SELECT 1 FROM users WHERE email = 'stefanpatrascu96+admin@gmail.com')
    BEGIN
        INSERT INTO users (first_name, last_name, password, email, role, avatar_file_name, doctor_info_id, patient_info_id)
        VALUES ('Stefan', 'Admin', @password, 'stefanpatrascu96+admin@gmail.com', 'ADMIN', null, null, null);
    END
