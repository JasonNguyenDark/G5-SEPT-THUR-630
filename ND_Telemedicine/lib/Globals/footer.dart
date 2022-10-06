import 'dart:async';
import 'dart:convert';
import 'dart:html';
import 'dart:io';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:nd_telemedicine/Globals/variables.dart';
import 'package:syncfusion_flutter_calendar/calendar.dart';
import 'package:mask_text_input_formatter/mask_text_input_formatter.dart';
import '../Models/Schedule.dart';



//duplicate footer class
class Footer extends StatefulWidget {
  const Footer({Key? key}) : super(key: key);

  @override
  State<Footer> createState() => FooterState();
}

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