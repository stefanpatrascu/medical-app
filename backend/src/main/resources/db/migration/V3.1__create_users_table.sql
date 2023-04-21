IF OBJECT_ID('dbo.doctor_info', 'U') IS NULL
BEGIN
CREATE TABLE doctor_info (
                             id BIGINT IDENTITY(1,1) PRIMARY KEY,
                             clinic_address NVARCHAR(255) NOT NULL
);
END;

IF OBJECT_ID('dbo.patient_info', 'U') IS NULL
BEGIN
CREATE TABLE patient_info (
                              id BIGINT IDENTITY(1,1) PRIMARY KEY,
                              address NVARCHAR(255) NOT NULL,
                              phone_number NVARCHAR(255) NOT NULL
);
END;

IF OBJECT_ID('dbo.users_info', 'U') IS NULL
BEGIN
CREATE TABLE users_info (
                            id BIGINT IDENTITY(1,1) PRIMARY KEY,
                            cnp NVARCHAR(255),
                            birth_date DATE
);
END;

IF OBJECT_ID('dbo.users', 'U') IS NULL
BEGIN
CREATE TABLE users (
                       id BIGINT IDENTITY(1,1) PRIMARY KEY,
                       first_name NVARCHAR(255) NOT NULL,
                       last_name NVARCHAR(255) NOT NULL,
                       password NVARCHAR(255) NOT NULL,
                       email NVARCHAR(255) NOT NULL UNIQUE,
                       role NVARCHAR(50) NOT NULL,
                       avatar_file_name NVARCHAR(255),
                       user_info_id BIGINT,
                       patient_info_id BIGINT,
                       doctor_info_id BIGINT,
                       FOREIGN KEY (user_info_id) REFERENCES users_info(id) ON DELETE CASCADE ON UPDATE CASCADE,
                       FOREIGN KEY (patient_info_id) REFERENCES patient_info(id) ON DELETE CASCADE ON UPDATE CASCADE,
                       FOREIGN KEY (doctor_info_id) REFERENCES doctor_info(id) ON DELETE CASCADE ON UPDATE CASCADE
);
END;
