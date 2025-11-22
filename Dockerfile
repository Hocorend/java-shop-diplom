# Use Eclipse Temurin (recommended replacement for OpenJDK)
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/diplom-0.0.2-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application runs on
EXPOSE 8085

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]