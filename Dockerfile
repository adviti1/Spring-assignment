# Use official OpenJDK image
FROM openjdk:17.0.8-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Package the app
RUN apt-get update && apt-get install -y maven
RUN mvn clean package

# Expose default port
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
