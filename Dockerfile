FROM maven:3.6.3-openjdk-8-slim AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package

FROM openjdk:8-jre-slim
COPY --from=builder /app/target/app.jar .
CMD ["java", "-jar", "/app.jar"]