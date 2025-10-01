# Etapa 1: Build do frontend (React)
FROM node:16-alpine AS frontend-build

WORKDIR /app

# Copia arquivos de dependências do frontend
COPY TCC-Frontend/package*.json ./
RUN npm install

# Copia o restante do frontend
COPY TCC-Frontend/ . 

# Gera a versão de produção do React
RUN npm run build


# Etapa 2: Build do backend (Spring Boot)
FROM maven:3.9.4-eclipse-temurin-17 AS backend-build

WORKDIR /app

# Copia o backend inteiro
COPY TCC-Backend/ . 

# Copia o build do frontend para dentro dos resources do Spring Boot
COPY --from=frontend-build /app/build ./src/main/resources/static

# Constrói o projeto Spring Boot (com frontend embutido)
RUN mvn clean package -DskipTests


# Etapa 3: Execução
FROM openjdk:17-jdk-slim

WORKDIR /app

EXPOSE 8080

# Copia o JAR gerado do backend
COPY --from=backend-build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
