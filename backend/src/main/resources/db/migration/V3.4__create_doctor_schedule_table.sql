-- Create the doctor_schedule table
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'doctor_schedule' AND type = 'U')
    BEGIN
        CREATE TABLE doctor_schedule (
                                         id INT IDENTITY(1,1) NOT NULL,
                                         doctor_info_id BIGINT NOT NULL,
                                         monday_start TIME DEFAULT '00:00:00',
                                         monday_end TIME DEFAULT '00:00:00',
                                         tuesday_start TIME DEFAULT '00:00:00',
                                         tuesday_end TIME DEFAULT '00:00:00',
                                         wednesday_start TIME DEFAULT '00:00:00',
                                         wednesday_end TIME DEFAULT '00:00:00',
                                         thursday_start TIME DEFAULT '00:00:00',
                                         thursday_end TIME DEFAULT '00:00:00',
                                         friday_start TIME DEFAULT '00:00:00',
                                         friday_end TIME DEFAULT '00:00:00',
                                         saturday_start TIME DEFAULT '00:00:00',
                                         saturday_end TIME DEFAULT '00:00:00',
                                         sunday_start TIME DEFAULT '00:00:00',
                                         sunday_end TIME DEFAULT '00:00:00',
                                         PRIMARY KEY (id),
                                         FOREIGN KEY (doctor_info_id) REFERENCES doctor_info (id)
        );
    END;

-- Add schedule_id column to doctor_info table
IF NOT EXISTS (SELECT * FROM sys.columns WHERE name = 'schedule_id' AND object_id = OBJECT_ID('doctor_info'))
    BEGIN
        ALTER TABLE doctor_info ADD schedule_id INT DEFAULT NULL;
    END;

-- Create foreign key constraint from doctor_info to doctor_schedule
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE name = 'fk_doctor_schedule_doctor_info_id' AND parent_object_id = OBJECT_ID('doctor_schedule'))
    BEGIN
        ALTER TABLE doctor_schedule ADD CONSTRAINT fk_doctor_schedule_doctor_info_id FOREIGN KEY (doctor_info_id) REFERENCES doctor_info(id);
    END;
