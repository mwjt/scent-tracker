FROM maven:3.9.1-eclipse-temurin-17-alpine

WORKDIR /backend
COPY . .
RUN mvn clean install

EXPOSE 8090
CMD mvn spring-boot:run