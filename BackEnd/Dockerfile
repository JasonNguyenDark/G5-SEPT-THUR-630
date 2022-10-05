FROM maven:4.00-jdk-18

# path
WORKDIR /BackEnd
# copy all
COPY . .
# run
RUN mvn clean install
#CMD: run spring boot
CMD mvn spring-boot:run

