# Этап сборки
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests
FROM eclipse-temurin:17-jre-alpine  # Alpine Linux — легковесный
WORKDIR /app
COPY --from=build /app/target/storehouse.jar .
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "storehouse.jar"]
