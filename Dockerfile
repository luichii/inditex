# Use a lightweight base image with Java support
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY main/target/main-0.0.1-SNAPSHOT.jar /app/main-0.0.1-SNAPSHOT.jar

# Specify the command to run your application
CMD ["java", "-jar", "/app/main-0.0.1-SNAPSHOT.jar"]
