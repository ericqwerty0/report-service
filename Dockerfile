FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:21-slim-bullseye
WORKDIR /app
COPY --from=build /app/target/report-service-0.0.1-SNAPSHOT.jar ./report-service.jar
CMD ["java", "-jar", "report-service.jar"]