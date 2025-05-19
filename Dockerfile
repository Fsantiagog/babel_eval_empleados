FROM openjdk:17-alpine

LABEL authors="felipe santiago"

WORKDIR /app

COPY target/empleados-0.0.1-SNAPSHOT.jar empleados-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "empleados-0.0.1-SNAPSHOT.jar"]