# Library API - Study Repository

## Description
Library API is a Spring Boot application designed to manage a library system. It provides endpoints for managing authors, books, and other library-related resources.

## Features
- RESTful API for library management.
- Integration with PostgreSQL for data persistence.
- Built using Spring Boot and Spring Data JPA.

## Prerequisites
- Java 17 or higher
- Maven 3.8 or higher
- PostgreSQL database

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/henriquecervi/estudoJavaSpringBoot.git
cd libraryapi
```

### Configure the Database
1. Ensure PostgreSQL is running.
2. Create a database named `library`:
   ```sql
   CREATE DATABASE library;
   ```
3. Update the `application.yml` file with your database credentials:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/library
       username: <your-username>
       password: <your-password>
   ```

### Build and Run the Application
1. Build the project using Maven:
   ```bash
   mvn clean install
   ```
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Access the API
The application will be available at `http://localhost:8080`.

## Endpoints
- **Authors**: `/autores`

## Development

### Project Structure
- `src/main/java`: Contains the source code.
- `src/main/resources`: Contains configuration files like `application.yml`.
- `HELP.md`: Reference documentation.

### Dependencies
- Spring Boot
- Spring Data JPA
- PostgreSQL Driver