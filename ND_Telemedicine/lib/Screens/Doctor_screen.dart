import 'dart:convert';
import 'dart:io';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:nd_telemedicine/Globals/variables.dart';
import 'package:syncfusion_flutter_calendar/calendar.dart';

import '../Models/schedule.dart';


// TODO: Fix display routing, get email from credientialstorage, addDatasource to Sfcalender

class DoctorScreenPage extends StatefulWidget {
  const DoctorScreenPage({Key? key}) : super(key: key);

  @override
  State<DoctorScreenPage> createState() => DoctorScreenPageState();
}

class DoctorScreenPageState extends State<DoctorScreenPage> {

  final TextEditingController roleController = TextEditingController(text: "");
  // Read role
  Future<void> readFromStorage() async {
    roleController.text = await credentialStorage.read(key: "Key_role") ?? '';
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(

      //Top bar/header
      appBar: AppBar(
          backgroundColor: Colors.blue,

          title: FutureBuilder<void> (
            future: readFromStorage(),
            builder: (BuildContext context, AsyncSnapshot<void> snapshot) {
              return Text(
                roleController.text,

                //all styling goes here
                style: const TextStyle(
                  fontFamily: 'Arvo',
                  color: Colors.black,
                  fontSize: 16.0,
                  fontWeight: FontWeight.bold,
                ),
              );
            },
          ),

          actions: <Widget>[
            //home
            DecoratedBox(
              decoration: const BoxDecoration(
                border: Border(
                  left: BorderSide(color: Colors.black),
                  right: BorderSide(color: Colors.black),
                ),
              ),
              child: TextButton(
                onPressed: () {},
                child: const Text(
                  'Home',
                  style: TextStyle(
                    fontFamily: 'Arvo',
                    color: Colors.black,
                    fontSize: 16.0,
                  ),
                ),
              ),
            ),

            // profile
            DecoratedBox(
              decoration: const BoxDecoration(
                border: Border(
                  left: BorderSide(color: Colors.black),
                  right: BorderSide(color: Colors.black),
                ),
              ),
              child: TextButton(
                onPressed: () {},
                child: const Text(
                  'profile',
                  style: TextStyle(
                    fontFamily: 'Arvo',
                    color: Colors.black,
                    fontSize: 16.0,
                  ),
                ),
              ),
            ),

            // Booking/Scheduling
            DecoratedBox(
                decoration: const BoxDecoration(
                  border: Border(
                    left: BorderSide(color: Colors.black),
                    right: BorderSide(color: Colors.black),
                  ),
                ),
                child: FutureBuilder<void>(
                    future: readFromStorage(),
                    builder:
                        (BuildContext context, AsyncSnapshot<void> snapshot) {
                      if (roleController.text == 'doctor') {
                        return TextButton(
                          onPressed: () {},
                          child: const Text(
                            'Schedule',
                            style: TextStyle(
                              fontFamily: 'Arvo',
                              color: Colors.black,
                              fontSize: 16.0,
                            ),
                          ),
                        );
                      } else {
                        return TextButton(
                          onPressed: () {},
                          child: const Text(
                            'Booking',
                            style: TextStyle(
                              fontFamily: 'Arvo',
                              color: Colors.black,
                              fontSize: 16.0,
                            ),
                          ),
                        );
                      }
                    })
            ),

            // Health Record/Prescription
            DecoratedBox(
                decoration: const BoxDecoration(
                  border: Border(
                    left: BorderSide(color: Colors.black),
                    right: BorderSide(color: Colors.black),
                  ),
                ),
                child: FutureBuilder<void>(
                    future: readFromStorage(),
                    builder:
                        (BuildContext context, AsyncSnapshot<void> snapshot) {
                      if (roleController.text == 'doctor') {
                        return TextButton(
                          onPressed: () {},
                          child: const Text(
                            'Prescribe',
                            style: TextStyle(
                              fontFamily: 'Arvo',
                              color: Colors.black,
                              fontSize: 16.0,
                            ),
                          ),
                        );
                      } else {
                        return TextButton(
                          onPressed: () {},
                          child: const Text(
                            'Record',
                            style: TextStyle(
                              fontFamily: 'Arvo',
                              color: Colors.black,
                              fontSize: 16.0,
                            ),
                          ),
                        );
                      }
                    })),

            // Chat
            DecoratedBox(
              decoration: const BoxDecoration(
                border: Border(
                  left: BorderSide(color: Colors.black),
                  right: BorderSide(color: Colors.black),
                ),
              ),
              child: TextButton(
                onPressed: () {},
                child: const Text(
                  'Chat',
                  style: TextStyle(
                    fontFamily: 'Arvo',
                    color: Colors.black,
                    fontSize: 16.0,
                  ),
                ),
              ),
            ),
          ]
      ),

      body: const Content(),
      //Footer
      bottomNavigationBar: const Footer(),

    );
  }
}

// Schedule
class Content extends StatefulWidget{
  const Content({Key? key}) : super(key: key);

  @override
  State<Content> createState() => ContentState();
}



class ContentState extends State<Content>{
  
  Widget build(BuildContext context) {
    return Scaffold(
    body: SfCalendar(
    view: CalendarView.week,
    ),
    floatingActionButton: FloatingActionButton(
    onPressed: () {
                showDialog(
                context: context,
                builder: (ctx) => AlertDialog(
                  title: const Text("Add schedule"),
                  content: const ScheduleForm(),
                  actions: <Widget>[
                    TextButton(
                      onPressed: () {
                        Navigator.of(ctx).pop();
                      },
                      child: Container(
                        padding: const EdgeInsets.all(14),
                        child: const Text("close"),
                      ),
                    ),
                  ],
                ),
              );
          },
        child: const Icon(Icons.add),
      ),
    );

  }


}

class ScheduleForm extends StatefulWidget{
    const ScheduleForm({super.key});

  @override
  ScheduleFormState createState() {
    return ScheduleFormState();
  }
}

class ScheduleFormState extends State<ScheduleForm> {
  final scheduleformKey = GlobalKey<FormState>();
  final TextEditingController emailController = TextEditingController(text: "");

  
  Future<void> readFromStorage() async {
    emailController.text = await credentialStorage.read(key: "Key_email") ?? '';
  }

  Schedule schedule = Schedule('', '', '', '');
  
  Future createSchedule() async{
    Map data ={
      'email' : emailController.text,
      'date' :  schedule.date,
      'startTime' : schedule.startTime,
      'duration' : schedule.duration,
    };

    
    Uri url = Uri.parse('${baseUrl}schedule/addSchedule');
    var body = jsonEncode(data);
    try {
      await http.post(
        url,
        headers: headers,
        body: body,
      );
    }
    catch (e) {
      print(e);
    }

  }




  @override
  Widget build(BuildContext context) {
    // Build a Form widget using the _formKey created above.
    return Form(
      key: scheduleformKey,
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: <Widget>[
          TextFormField(  
            decoration: const InputDecoration(
            icon: Icon(Icons.date_range),
            hintText: 'Day Month Year',
            labelText: 'Date',
            ),
            onChanged: (value) {
              schedule.date = value;
            },
          ),
          TextFormField(  
            decoration: const InputDecoration(
            icon: Icon(Icons.check_circle),
            hintText: 'Exact Hour Minute',
            labelText: 'Start time',
            ),
            onChanged: (value) {
            schedule.startTime = value;
            },        
          ),
          TextFormField(  
            decoration: const InputDecoration(
            icon: Icon(Icons.timelapse),
            hintText: 'Hour',
            labelText: 'Duration',
            ),
            onChanged: (value) {
            schedule.duration = value;
            },
          ),

          Container(
          margin: const EdgeInsets.only(right: 2.0),
          child: Padding(
            padding: const EdgeInsets.symmetric(
                horizontal: 10.0, vertical: 6.0),
            child: ElevatedButton(

              //button text
              child: const Text('Add'),
              //button logic/functionality when pressed
              onPressed: () async {
                createSchedule();
              },
            ),
          ),
        ),
        ],
      ),
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
