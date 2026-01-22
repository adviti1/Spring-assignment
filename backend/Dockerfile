# backend/Dockerfile

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

COPY demo/mvnw demo/pom.xml ./
COPY demo/.mvn .mvn
COPY demo/src ./src

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 8080
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
