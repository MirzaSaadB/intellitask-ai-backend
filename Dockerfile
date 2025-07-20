# Use an official OpenJDK 17 runtime as a base image
FROM openjdk:17-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/*.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file when the container launches
ENTRYPOINT ["java","-jar","/app.jar"]