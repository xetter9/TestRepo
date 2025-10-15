# Use a lightweight Java image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built jar into the container
COPY target/spring-kafka-demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Start the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
