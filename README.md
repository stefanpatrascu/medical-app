# MedicalAppointmentApp

This project consists of three parts: the frontend, built with Angular, the backend, developed using Java 17 with Maven, and the Microsoft SQL Server instance. 
The project includes a `docker-compose.yml` file for running separate containers for each 
component. 
## Repository Structure

The repository contains two main directories:

1. `frontend/`: This directory contains the Angular 14 project. It holds all the necessary frontend assets, components, and logic.

2. `backend/`: This directory contains the Java 17 Maven project, which handles all the backend logic, API requests, and database connections.

## Backend Configuration

The backend Java configuration files can be found in `backend/src/main/resources`. The main configuration file is `application.yml`, which contains settings related to the database connection, server port, and other project-specific configurations.

If you need to override any configuration properties for production, you can update the existing file named `application.prod.yml` in the same directory. This file will be used by default when the application is run using Docker Compose.

Please review and update the configuration settings in the appropriate file(s) as needed for your environment.

## Running Java, Angular, and Microsoft SQL Server from Docker

To run frontend, backend and Microsoft SQL Server directly from Docker, follow these steps:

1. Clone the repository.
2. Navigate to the root directory of the project.
3. Run the following commands one by one to start the **SQL Server**, **Backend** and **Frontend** containers:

```bash
docker-compose up -d --build --force-recreate db
docker-compose up -d --build --force-recreate backend
docker-compose up -d --build --force-recreate frontend
```
4. Wait a few seconds for the containers to start up completely


## Additional Information

Please make sure to update the README with any other relevant information as the project evolves. This may include new features, dependencies, or important updates. Keep the README up-to-date


<a rel="license" href="http://creativecommons.org/licenses/by-nc/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc/4.0/">Creative Commons Attribution-NonCommercial 4.0 International License</a>.
