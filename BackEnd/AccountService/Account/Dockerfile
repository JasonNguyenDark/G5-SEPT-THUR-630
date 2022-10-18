FROM maven:3.8.6-eclipse-temurin-19-alpine

WORKDIR /Account
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run