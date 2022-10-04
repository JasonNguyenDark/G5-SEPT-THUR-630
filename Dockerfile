FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY BackEnd/target/*.jar BackEnd-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/BackEnd-0.0.1-SNAPSHOT.jar"]