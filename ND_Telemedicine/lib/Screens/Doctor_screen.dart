import 'dart:js';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:nd_telemedicine/Globals/variables.dart';

class DoctorScreenPage extends StatefulWidget {
  const DoctorScreenPage({Key? key}) : super(key: key);

  @override
  State<DoctorScreenPage> createState() => doctorScreenPageState();
}

class DoctorScreenPageState extends State<DoctorScreenPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
    //  TODO: enter a calendar field to add to the doctor's profile

    );
  }
}

//duplicate footer class
class Footer extends StatefulWidget {
  const Footer({Key? key}) : super(key: key);

  @override
  State<Footer> createState() => FooterState();
}

//duplicate footer class
class FooterState extends State<Footer> {
  @override
  Widget build(BuildContext context) {
    return BottomAppBar(
      color: Colors.blue,

      child: Container(
        margin: const EdgeInsets.fromLTRB(10, 15, 10, 15),

        child: const Text(
          '© NY Telemedicine 2022',
          style: TextStyle(
            fontFamily: 'Arvo',
            color: Colors.black,
            fontSize: 16.0,
          ),
        ),
      ),
    );
  }
}
