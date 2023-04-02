FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

# Copy the entire content of the current directory into the /app directory in the container
COPY . .

# Run mvn clean package without skipping tests
RUN mvn clean package -DskipTests

FROM maven:3.8.3-openjdk-17 AS run

WORKDIR /app

# Copy the application.docker.properties file into the run phase, in the correct directory for Spring Boot
COPY --from=build /app/src/main/resources/application.docker.properties /app/config/application.properties

# Copy the JAR file
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8089

# Modify the command to specify the config directory
CMD ["java", "-jar", "app.jar", "--spring.config.location=file:/app/config/"]
