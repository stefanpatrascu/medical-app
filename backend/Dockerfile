FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM maven:3.8.3-openjdk-17 AS run

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8089

CMD ["java", "-jar", "app.jar"]
