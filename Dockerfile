# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/diplom-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]