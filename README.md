Flutter:

ND_Telemedicine = Flutter folder
BackEnd = springboot/java

Download flutter 3.0.5-Stable and follow the rest of the instructions:
https://docs.flutter.dev/get-started/install/windows

Config:
project Type: Application
android language: Java
iOS language: Swift

platforms: Android, iOS, Web, Windows

IMPORTANT:
change the file in BackEnd/src/main/resources/application.properties if you want to run it locally or via cloud
username is for the username of the user of the database
password is the password of the user of the database
url is the database url 
server port is which port you want to host the spring boot server on

tables
user = Fields are id(int, primary key auto-increment is on), name, surname, gender, email, password
doctor = fields are the same as user for now
record = Fields are id(int, primary key auto-increment is on), name, surname, gender, email, allergies, status



Bugs:
This version is for web
Im having issues with configuring for android, so for now this is the working version
