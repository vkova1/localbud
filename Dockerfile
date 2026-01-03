# --- Stage 1: Build the JAR ---
# Use the smallest JDK image for building
FROM eclipse-temurin:21-jdk-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the build files (Gradle wrapper, settings, etc.)
COPY gradlew .
COPY gradle gradle

# Copy all source files
COPY . .

# Build the app: Use a full path for the wrapper and remove '-x test'
# unless you are sure tests are breaking the build (it's better to run them).
# We add a dummy ENV change to force a cache break on the compile step if needed.
ENV BUILD_DATE=1
RUN chmod +x gradlew && ./gradlew clean build --no-daemon

# Clean up the unnecessary plain JAR
RUN rm build/libs/*-plain.jar


# --- Stage 2: Run the Application ---
# Use the smallest JRE image for running
FROM eclipse-temurin:21-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Set the entrypoint for the JVM
ENTRYPOINT ["java", "-jar", "/app/app.jar"]