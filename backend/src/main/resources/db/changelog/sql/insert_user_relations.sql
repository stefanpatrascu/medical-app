BEGIN TRANSACTION;

-- CREATE DOCTOR ACCOUNT
INSERT INTO users (email, password, role)
VALUES ('stefanpatrascu96+doctor_11@gmail.com', '$2a$10$bq2FgYCCnRabeEjLc/GzXuq8FdYYXXate9V/rtecONR5JKNp6ruHW', 'DOCTOR');

INSERT INTO doctor_info (specialty, clinic_address, user_id)
VALUES ('Cardiology', '123 Main St', SCOPE_IDENTITY());

UPDATE users SET doctor_info_id = SCOPE_IDENTITY() WHERE email = 'stefanpatrascu96+doctor_11@gmail.com';


-- CREATE PATIENT ACCOUNT

INSERT INTO users (email, password, role)
VALUES ('stefanpatrascu96+patient_11@gmail.com', '$2a$10$bq2FgYCCnRabeEjLc/GzXuq8FdYYXXate9V/rtecONR5JKNp6ruHW', 'PATIENT');

INSERT INTO patient_info (address, phone_number, user_id)
VALUES ('Str. Calea Bucuresti 227', '0000000000', SCOPE_IDENTITY());

UPDATE users SET patient_info_id = SCOPE_IDENTITY() WHERE email = 'stefanpatrascu96+patient_11@gmail.com';


-- CREATE ADMIN ACCOUNT

INSERT INTO users (email, password, role)
VALUES ('admin@admin.com', '$2a$10$bq2FgYCCnRabeEjLc/GzXuq8FdYYXXate9V/rtecONR5JKNp6ruHW', 'PATIENT');


COMMIT;
