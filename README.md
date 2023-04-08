# Medical App

This repository contains a Docker-based project for a Medical App built with Spring Boot and Angular 15. This README will guide you through the process of setting up and running the project locally on Linux, macOS, and Windows.

## Project Structure

The repository contains two main directories:

- `frontend/`: This directory contains the Angular 15 project. It holds all the necessary frontend assets, components, and logic.

- `backend/`: This directory contains the Java 17 Spring Boot Maven project, which handles all the backend logic, API requests, and database connections.

## Prerequisites

To run this project, you need to have Docker installed on your local machine. Here are the installation guides for different operating systems:

- [Docker Desktop for Windows](https://docs.docker.com/desktop/windows/install/)
- [Docker Desktop for macOS](https://docs.docker.com/desktop/mac/install/)
- [Docker for Linux](https://docs.docker.com/engine/install/)

## Configuration

1. After installing Docker, navigate to the root folder of the project.

2. Create a `.env` file in the root folder with the following configurations:


```bash
DB_USERNAME=sa
DB_PASSWORD=MyStrongP@ssw0rd
JWT_SECRET=qiBaPmXamTMorenafQzeK7GOOA
APP_ADMIN_EMAIL=admin@admin.com
APP_ADMIN_PASSWORD=admin
```
## Running the Project

The application will run automatically on the `docker-local` profile.

1. Open the terminal or command prompt, and navigate to the root folder of the project.

2. Run the following command:

```docker-compose up -d --build --force-recreate```

This command will build and start the Docker containers, as defined in the `docker-compose.yml` file, in detached mode.

3. Once the containers are up and running, you can access the Medical App locally:

- Backend: [http://localhost:8089/](http://localhost:8089/)
- Frontend: [http://localhost:4209/](http://localhost:4209/)

4. Log in to the application using the `APP_ADMIN_EMAIL` and `APP_ADMIN_PASSWORD` values specified in the `.env` file.

## Key Features


//TODO

## Stopping the Project

To stop the running containers and remove them, run the following command in the terminal or command prompt:

```docker-compose down```


For more information and advanced usage, please refer to the [Docker Compose documentation](https://docs.docker.com/compose/).

## Additional Information

Please make sure to update the README with any other relevant information as the project evolves. This may include new features, dependencies, or important updates. Keep the README up-to-date


<a rel="license" href="http://creativecommons.org/licenses/by-nc/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc/4.0/">Creative Commons Attribution-NonCommercial 4.0 International License</a>.
