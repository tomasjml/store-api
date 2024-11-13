# Use the Gradle image with JDK 21 to both build and run the application
FROM gradle:jdk21 AS build
LABEL authors="tomas-molina"

# Set the working directory
WORKDIR /app

# Copy the Gradle files and source code
COPY build.gradle settings.gradle ./
COPY config ./config
COPY src ./src

# Build the application
RUN gradle clean build -x test

# Expose the application port
EXPOSE 9090

# Run the application directly from the build output
ENTRYPOINT ["java", "-jar", "build/libs/store-api-0.0.1-SNAPSHOT.jar"]
