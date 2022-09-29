Flutter:

ND_Telemedicine = Flutter folder
BackEnd = springboot/java

First download flutter 3.0.5-Stable and follow the rest  of the instruction:
https://docs.flutter.dev/get-started/install/windows

Config:

project Type: Application
android language: Java
iOS language: Swift

platforms: Android, iOS, Web, Windows

IMPORTANT:
change the file in BackEnd/src/main/resources/application.properties if you want to run it locally or via cloud

tables
user = Fields are id(int, primary key auto-increment is on), name, surname, gender, email, password
doctor = fields are the same as user for now
record = Fields are id(int, primary key auto-increment is on), name, surname, gender, email, allergies, status
prescription = Fields are id(int, primary key auto-increment is on), date(type = DATE), description, doctorName, medicine, patientEmail, patientName

For Android:
First make sure you have virtualization enabled in your pc's BIOS
Java is the language that is being used

Download(make sure you have around 5-10gb of space):
Android Studio and then download flutter and dart plugins(if i remember correctly you will have to config sdk path for flutter plugin)
SetUp:
https://www.geeksforgeeks.org/android-studio-setup-for-flutter-development/
AVD config:
https://developer.android.com/studio/run/managing-avds

if there any trouble i can help during our team meeting - Richard

Currently reworking the profile feature
