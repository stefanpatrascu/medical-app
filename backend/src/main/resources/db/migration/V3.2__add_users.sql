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

        INSERT INTO users_info (first_name, last_name)
        OUTPUT INSERTED.id
        VALUES ('Stefan', 'Doctor');

        DECLARE @doctor_user_info_id BIGINT;
        SET @doctor_user_info_id = SCOPE_IDENTITY();

        INSERT INTO users (password, email, role, user_info_id, doctor_info_id)
        VALUES (@password, 'stefanpatrascu96+doctor@gmail.com', 'DOCTOR', @doctor_user_info_id, @doctor_info_id);

        INSERT INTO doctor_specialization (doctor_info_id, specialization_id)
        VALUES (@doctor_info_id, (SELECT TOP 1 MIN(id) FROM medical_specializations ));

        INSERT INTO doctor_specialization (doctor_info_id, specialization_id)
        VALUES (@doctor_info_id, (SELECT TOP 1 MAX(id) FROM medical_specializations ));
    END

-- Insert patient if not already in the users table
IF NOT EXISTS (SELECT 1 FROM users WHERE email = 'stefanpatrascu96+patient@gmail.com')
    BEGIN
        INSERT INTO users_info (first_name, last_name)
        OUTPUT INSERTED.id
        VALUES ('Stefan', 'Patient');

        DECLARE @patient_user_info_id BIGINT;
        SET @patient_user_info_id = SCOPE_IDENTITY();

        INSERT INTO users (password, email, role, user_info_id, doctor_info_id)
        VALUES (@password, 'stefanpatrascu96+patient@gmail.com', 'PATIENT', @patient_user_info_id, null);
    END

-- Insert admin if not already in the users table
IF NOT EXISTS (SELECT 1 FROM users WHERE email = 'stefanpatrascu96+admin@gmail.com')
    BEGIN
        INSERT INTO users_info (first_name, last_name)
        OUTPUT INSERTED.id
        VALUES ('Stefan', 'Admin');

        DECLARE @admin_user_info_id BIGINT;
        SET @admin_user_info_id = SCOPE_IDENTITY();

        INSERT INTO users (password, email, role, user_info_id, doctor_info_id)
        VALUES (@password, 'stefanpatrascu96+admin@gmail.com', 'ADMIN', @admin_user_info_id, null);
    END
