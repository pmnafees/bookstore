# Bookstore Backend

## Project Overview
A Spring Boot backend for managing books. 
It exposes RESTful CRUD APIs, uses JPA for database operations, and includes a working CI/CD pipeline using GitHub Actions. 
The backend is containerized with Docker and the built image is pushed to GitHub Container Registry (GHCR).

## Tech Stack
- Java 17
- Spring Boot 3.x
- Maven
- Spring Data JPA
- PostgreSQL (runtime)
- Docker
- GitHub Actions (CI/CD)

## Running the Project

### Local Setup
1. Install Java 17 and Maven.
2. Set your database config in src/main/resources/application.properties.
3. Run:
   mvn spring-boot:run

### Docker Setup
1. Build:
   docker build -t bookstore:latest .
2. Run:
   docker run -p 8080:8080 bookstore:latest

### Swagger UI
Open:
http://localhost:8080/swagger-ui/index.html
