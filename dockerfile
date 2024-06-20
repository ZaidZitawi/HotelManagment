# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the project's build output (JAR file) to the container
COPY target/HotelManagment-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Define the entry point for the container to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
