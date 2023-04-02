IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'medicalApp')
    CREATE DATABASE medicalApp;
GO

USE medicalApp;
GO

IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = 'medicalApp_login')
    CREATE LOGIN medicalApp_login WITH PASSWORD = 'not@dm1n';
GO

IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = 'medicalApp_user')
    CREATE USER medicalApp_user FOR LOGIN medicalApp_login;
GO

ALTER ROLE db_owner ADD MEMBER medicalApp_user;
GO
