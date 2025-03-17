# Etapa 1: Build da aplicação
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Execução da aplicação
FROM azul/zulu-openjdk:17 AS runtime
WORKDIR /app
COPY --from=build /app/target/product-manager-0.0.1-SNAPSHOT.jar product-manager.jar
ENTRYPOINT ["java", "-jar", "product-manager.jar"]
