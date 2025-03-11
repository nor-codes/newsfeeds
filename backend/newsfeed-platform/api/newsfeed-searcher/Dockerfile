# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Micronaut application JAR (Ensure it's built first)
COPY build/libs/*.jar app.jar

# Expose the application's port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
