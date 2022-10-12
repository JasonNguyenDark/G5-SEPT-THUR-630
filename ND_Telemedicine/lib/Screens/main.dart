import 'package:flutter/material.dart';
import 'package:nd_telemedicine/Screens/add.dart';
import 'package:nd_telemedicine/Screens/prescription.dart';
import 'package:nd_telemedicine/Screens/profile.dart';
import 'package:nd_telemedicine/Screens/record.dart';
import 'package:nd_telemedicine/Screens/signup.dart';
import 'package:nd_telemedicine/Screens/landing.dart';
import 'package:nd_telemedicine/Screens/home.dart';
import 'DoctorSchedule.dart';
import 'PatientBooking.dart';
import '../Globals/footer.dart';



void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(

      title: 'NYT',

      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),

      // default page/first page user will land on
      initialRoute: '/landing',

      // routing for frontend goes here
      routes: {
        '/landing' : (context) => const LandingPage(),
        '/signup' : (context) => const SignUpPage(),
        '/home' : (context) => const HomePage(),
        '/record' : (context) => const RecordPage(),
        '/prescription' : (context) => const PrescriptionPage(),
        '/profile' : (context) => const ProfilePage(),
        '/add' : (context) => const AddPage(),
        '/schedule'  : (context) => const DoctorScreenPage(),
        '/booking' :(context) => const BookingPage()
      },

    );
  }
}

