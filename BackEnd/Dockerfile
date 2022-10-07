FROM maven:3.8.6-openjdk-18

# path
WORKDIR ./BackEnd
# copy all
COPY . .

# run spring boot
CMD mvn clean install; mvn spring-boot:run

