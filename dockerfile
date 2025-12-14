# --- Stage 1: Build the JAR ---
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /src
COPY . .
# Build the app
RUN ./gradlew clean build -x test --no-daemon
RUN rm build/libs/*-plain.jar

# --- Stage 2: Run the Application ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Now this wildcard matches only ONE file, so it works perfectly
COPY --from=builder /src/build/libs/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]