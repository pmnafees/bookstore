# === Stage 1: Build the application ===
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first for caching
COPY pom.xml .
RUN mvn -q -e -B dependency:go-offline

# Copy source code
COPY src ./src

# Build Spring Boot JAR
RUN mvn -q -e -B package -DskipTests


# === Stage 2: Run the application ===
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy only the built JAR
COPY --from=build /app/target/*.jar app.jar

# Expose the port Spring uses
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
