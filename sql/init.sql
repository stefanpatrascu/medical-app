IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'medicalApp')
    CREATE DATABASE medicalApp;
GO

IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'medicalApp_test')
    CREATE DATABASE medicalApp_test;
GO
