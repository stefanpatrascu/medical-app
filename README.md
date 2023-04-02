# MedicalAppointmentApp

This project consists of three parts: the frontend, built with Angular, the backend, developed using Java 17 with Maven, and the Microsoft SQL Server instance. 
The project includes a `docker-compose.yml` file for running separate containers for each 
component. The containers for the backend and SQL Server will be generated alongside the
container for Angular.
## Repository Structure

The repository contains two main directories:

1. `frontend/`: This directory contains the Angular 15 project. It holds all the necessary frontend assets, components, and logic.

2. `backend/`: This directory contains the Java 17 Maven project, which handles all the backend logic, API requests, and database connections.

## Backend Configuration

The backend configuration can be found in `backend/src/main/resources/application.properties`. This file contains settings related to the database connection, server port, and other project-specific configurations.

Please review and update the configuration settings as needed for your environment. Additionally, please ensure that the `docker-compose.yml` file in the project root directory is also reviewed and configured as necessary.

## Running Java, Angular, and Microsoft SQL Server from Docker

To run frontend, backend and Microsoft SQL Server directly from Docker, follow these steps:

1. Clone the repository.
2. Navigate to the root directory of the project.
3. Run the following command to start the SQL Server and backend containers:

```bash
docker-compose up -d --build --force-recreate db
docker-compose up -d --build --force-recreate backend
```
4. Wait a few seconds for the containers to start up completely


## Additional Information

Please make sure to update the README with any other relevant information as the project evolves. This may include new features, dependencies, or important updates. Keep the README up-to-date


<a rel="license" href="http://creativecommons.org/licenses/by-nc/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc/4.0/">Creative Commons Attribution-NonCommercial 4.0 International License</a>.
