IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'appointments' AND type = 'U')
BEGIN

CREATE TABLE appointments
(
    id                    BIGINT IDENTITY(1,1) PRIMARY KEY,
    doctor_id             BIGINT    NOT NULL,
    patient_id            BIGINT    NOT NULL,
    appointment_date_time DATETIME2 NOT NULL,
    notes                 NVARCHAR(500),
    FOREIGN KEY (doctor_id) REFERENCES users (id),
    FOREIGN KEY (patient_id) REFERENCES users (id)
);

END;
