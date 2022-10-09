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
import '../Globals/footer.dart';


class BookingPage extends StatefulWidget {
  const BookingPage({Key? key}) : super(key: key);

  @override
  State<BookingPage> createState() => BookingPageState();
}

class BookingPageState extends State<BookingPage> {
  
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

// Booking
class Content extends StatefulWidget{
  const Content({Key? key}) : super(key: key);
  @override
  State<Content> createState() => ContentState();
}



class ContentState extends State<Content>{
  final TextEditingController emailController = TextEditingController(text: "");
  readEmailStorage() async {
  emailController.text = await credentialStorage.read(key: "Key_email") ?? '';
  }
  bool bookable = true;
  _AppointmentDataSource _getCalendarDataSource(){
  List<Appointment> appointments = <Appointment>[];
  return _AppointmentDataSource(appointments);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          FutureBuilder(
          future: getSchedules(),
          builder: (BuildContext context, AsyncSnapshot snapshot) {
            if (snapshot.data != null) {
              if(_AppointmentDataSource(snapshot.data).appointments?.length != 0){
              bookable = false;}
              return SafeArea(
                child: Container(
                    child: SfCalendar(
                      view: CalendarView.schedule,
                      timeZone:"AUS Eastern Standard Time",
                      dataSource: _AppointmentDataSource(snapshot.data),
                      )
                    )
                  );
            } else {
              return Container(
                child: Center(
                  child: Text('loading...'),
                    ),
                  );
                }
              },
            ),
        FutureBuilder(
          future: getBookable(),
          builder: (BuildContext context, AsyncSnapshot snapshot2) {
          if(bookable == true){
            // print(schedule);
            print(snapshot2.data);
            return Container(
                child: Center(
                  child: Text('Bookable'),
                    ),
                  );
          }
          else{
              return Container(
                child: Center(
                  child: Text('Already Booked'),
                    ),
                  );
          }
          }
        )
        ]
      ),
    );
  }

  
Future <List<Appointment>> getSchedules() async {
    readEmailStorage();
    await Future.delayed(Duration(seconds: 1));
    List<Appointment> appointments = <Appointment>[];
    List<Schedule> schedules;
    http.Response response;
    Map data = {
      'email' : emailController.text,
    };
    // print(data);
    Uri url = Uri.parse("${baseUrl}appointment/getBookedAppointment");
    String body = jsonEncode(data);
    response = await http.post(
      url,
      headers: headers,
      body: body
    );
    // print(response.body);
    schedules=(jsonDecode(response.body) as List).map((i) =>  
      Schedule.fromJson(i)).toList();
      // print(schedules.length);
      var index = 0;
      while(index < schedules.length){
        if (schedules[index].date != null) {
        String? curDate =schedules[index].date;
        String? curStime = schedules[index].startTime;
        var curDuration = int.parse(schedules[index].duration.toString());
        String dt1 = '$curDate $curStime';
        DateTime dt = DateTime.parse(dt1);
        String docName = schedules[index].email.toString();
        // print(dt);
            appointments.add(Appointment(
            startTime: dt,
            endTime: dt.add(Duration(hours:curDuration)),
            startTimeZone: "AUS Eastern Standard Time",
            endTimeZone: "AUS Eastern Standard Time",  
            subject: docName,
            color: Color.fromARGB(255, 29, 189, 23)
              )
              
            );

        }
        index = index + 1;
    }
    
    return appointments;
  }
  
  Future <Map> getBookable() async{
    readEmailStorage();
    await Future.delayed(Duration(seconds: 1));
    List<Schedule> schedules;
    Map returned = new Map();
    http.Response response;
    Map data = {
      'email' : emailController.text,
    };
    Uri url = Uri.parse("${baseUrl}schedule/Bookable");
    String body = jsonEncode(data);
    response = await http.post(
      url,
      headers: headers,
      body: body,
    );
    schedules=(jsonDecode(response.body) as List).map((i) =>  
      Schedule.fromJson(i)).toList();
    var index = 0;
    while(index < schedules.length){
      String detail = schedules[index].email.toString() 
      + ' ' + schedules[index].date.toString() 
      + ' ' + schedules[index].startTime.toString() 
      + ' Duration: ' + schedules[index].duration.toString() + ' Hour';
      print(detail);
      int? scheduleId = schedules[index].id;
      returned.update(scheduleId, (value) => detail);
    }  
    return returned;
  }

}

class _AppointmentDataSource extends CalendarDataSource {
  _AppointmentDataSource(List<Appointment> source){
   appointments = source; 
  }
}







