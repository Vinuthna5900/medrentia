# Use Eclipse Temurin OpenJDK 17 (official, actively maintained)
FROM eclipse-temurin:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy all files from the host to the container
COPY . .

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build the project and skip tests (you can remove -DskipTests if you want tests)
RUN ./mvnw clean package -DskipTests

# Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080

# Run the Spring Boot jar
CMD ["java", "-jar", "target/server-0.0.1-SNAPSHOT.jar"]
