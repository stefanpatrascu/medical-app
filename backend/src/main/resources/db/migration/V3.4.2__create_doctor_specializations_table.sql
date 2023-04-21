IF NOT EXISTS (SELECT *
               FROM sys.objects
               WHERE object_id = OBJECT_ID(N'dbo.doctor_specialization')
                 AND type in (N'U'))
    BEGIN
        CREATE TABLE dbo.doctor_specialization
        (
            doctor_info_id    int NOT NULL,
            specialization_id int NOT NULL,
            CONSTRAINT PK_doctor_specialization PRIMARY KEY CLUSTERED
                (
                 doctor_info_id ASC,
                 specialization_id ASC
                    ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        )

        ALTER TABLE dbo.doctor_specialization
            WITH CHECK ADD CONSTRAINT FK_doctor_specialization_DoctorInfo FOREIGN KEY (doctor_info_id)
                REFERENCES dbo.doctor_info (id)

        ALTER TABLE dbo.doctor_specialization
            CHECK CONSTRAINT FK_doctor_specialization_DoctorInfo

        ALTER TABLE dbo.doctor_specialization
            WITH CHECK ADD CONSTRAINT FK_doctor_specialization_MedicalSpecialization FOREIGN KEY (specialization_id)
                REFERENCES dbo.medical_specializations (id)

        ALTER TABLE dbo.doctor_specialization
            CHECK CONSTRAINT FK_doctor_specialization_MedicalSpecialization
    END

-- add doctor specialization for an existing user

INSERT INTO doctor_specialization (doctor_info_id, specialization_id)
VALUES ((SELECT TOP 1 doctor_info_id
         FROM dbo.users
         where role = 'DOCTOR'), 7);
