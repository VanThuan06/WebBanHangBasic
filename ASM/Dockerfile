FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

RUN chmod +x mvnw

#RUN --mount=type=cache,target=/root/.m2,rw ./mvnw -B package
RUN ./mvnw -B package
FROM openjdk:18.0.1.1

EXPOSE 8080

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]