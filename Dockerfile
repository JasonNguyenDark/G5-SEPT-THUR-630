FROM openjdk:18
VOLUME /tmp

# Copy the jar file into anything.jar
COPY BackEnd/target/BackEnd-0.0.1-SNAPSHOT.jar anything.jar
ENTRYPOINT ["java", "-jar", "/anything.jar"]