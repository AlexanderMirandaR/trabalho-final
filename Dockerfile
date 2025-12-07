# Stage 1: Build
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY backend/ ./backend/
RUN cd backend && mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/backend/target/api-1.0.0.jar .
EXPOSE 8080
CMD ["java", "-jar", "api-1.0.0.jar"]
