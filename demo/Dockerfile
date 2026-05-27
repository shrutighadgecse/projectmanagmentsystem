# Stage 1: Build the Spring Boot app
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies (improves build cache)
COPY pom.xml /app/
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY . /app

# Build the project and skip tests
RUN mvn clean package -DskipTests

# Stage 2: Run the built JAR
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the jar from build stage
COPY --from=build /app/target/*.jar app.jar



# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
