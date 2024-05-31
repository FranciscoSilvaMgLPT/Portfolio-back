FROM --platform=$BUILDPLATFORM maven:3.8.5-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src /app/src
RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim AS runtime
WORKDIR /app
COPY --from=builder /app/target/db-0.0.1-SNAPSHOT.jar /app/db-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/db-0.0.1-SNAPSHOT.jar"]
