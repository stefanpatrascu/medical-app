-- Define the start and end times of the working day
DECLARE @start_time TIME = '08:00:00';
DECLARE @end_time TIME = '16:00:00';

-- Create a new doctor schedule for each doctor user
INSERT INTO doctor_schedule (doctor_info_id, monday_start, monday_end, tuesday_start, tuesday_end, wednesday_start, wednesday_end, thursday_start, thursday_end, friday_start, friday_end)
SELECT
    u.doctor_info_id,
    @start_time,
    @end_time,
    @start_time,
    @end_time,
    @start_time,
    @end_time,
    @start_time,
    @end_time,
    @start_time,
    @end_time
FROM
    users u
WHERE
        u.role = 'DOCTOR';

-- Update the doctor_info table with the doctor_schedule_id values
UPDATE doctor_info SET schedule_id = ds.id FROM doctor_schedule ds WHERE ds.doctor_info_id = doctor_info.id;
