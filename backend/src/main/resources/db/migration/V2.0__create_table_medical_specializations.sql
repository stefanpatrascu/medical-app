IF OBJECT_ID('dbo.medical_specializations', 'U') IS NULL
BEGIN
CREATE TABLE medical_specializations (
                                         id BIGINT IDENTITY(1,1) PRIMARY KEY,
                                         label NVARCHAR(255) NOT NULL
);
END;
