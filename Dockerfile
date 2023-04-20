# Use the official OpenJDK image as a base image
FROM openjdk:8-jre-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the application's port
EXPOSE 4798

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]