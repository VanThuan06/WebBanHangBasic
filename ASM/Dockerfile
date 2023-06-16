# FROM openjdk:18.0.1.1-slim-buster as builder
FROM maven:3.9.2-eclipse-temurin-11 as m

WORKDIR /app
COPY . .

#RUN --mount=type=cache,target=/root/.m2,rw ./mvnw -B package
RUN mvn package
FROM openjdk:18.0.1.1

EXPOSE 8080

WORKDIR /app
COPY --from=m /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]