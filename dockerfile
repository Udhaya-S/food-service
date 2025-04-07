# Use the official OpenJDK image from Docker Hub
FROM openjdk:17-jdk-slim

# Copy the jar file from the target folder (assuming it's built using Maven/Gradle)
COPY target/food-service-0.0.1-SNAPSHOT.jar myapp.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "myapp.jar"]
