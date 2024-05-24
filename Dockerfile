# syntax=docker/dockerfile:1.4

FROM --platform=$BUILDPLATFORM maven:3.8.5-eclipse-temurin-17 AS builder
WORKDIR /Documents/Portfolio/back
COPY pom.xml pom.xml
RUN mvn dependency:go-offline

COPY src /Documents/Portfolio/back/src/
RUN mvn install -DskipTests

FROM builder AS dev-envs
RUN apt-get update && apt-get install -y --no-install-recommends git

RUN useradd -s /bin/bash -m vscode \
    && groupadd docker \
    && usermod -aG docker vscode

# install Docker tools (cli, buildx, compose)
COPY --from=gloursdocker/docker / /

CMD ["mvn", "spring-boot:run"]

FROM builder as prepare-production
RUN mkdir -p target/dependency
WORKDIR /Documents/Portfolio/back/target/dependency
RUN jar -xf ../*.jar

FROM eclipse-temurin:17-jre-focal
EXPOSE 8080
VOLUME /tmp
ARG DEPENDENCY=/Documents/Portfolio/back/target/dependency
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=prepare-production ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.db.DbApplication"]
