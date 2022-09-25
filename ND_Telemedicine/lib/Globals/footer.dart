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


// TODO: Fix display routing, addDatasource to Sfcalender



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
  final TextEditingController emailController = TextEditingController(text: "");
  readEmailStorage() async {
  emailController.text = await credentialStorage.read(key: "Key_email") ?? '';
  }
  
  _AppointmentDataSource _getCalendarDataSource(){
  List<Appointment> appointments = <Appointment>[];
  return _AppointmentDataSource(appointments);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: FutureBuilder(
          future: getSchedules(),
          builder: (BuildContext context, AsyncSnapshot snapshot) {
            if (snapshot.data != null) {
              return SafeArea(
                child: Container(
                    child: SfCalendar(
                      view: CalendarView.week,
                      timeZone:"AUS Eastern Standard Time",
                      dataSource: _AppointmentDataSource(snapshot.data),
                    )
                    ));
            } else {
              return Container(
                child: Center(
                  child: Text('Error'),
                ),
              );
            }
          },
        ),
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


  Future <List<Appointment>> getSchedules() async {
    List<Appointment> appointments = <Appointment>[];
    List<Schedule> schedules;
    http.Response response;
    Map data = {
      'email' : emailController.text,
    };
    
    Uri url = Uri.parse("${baseUrl}schedule/getSchedule");
    String body = jsonEncode(data);
    response = await http.post(
      url,
      headers: headers,
      body: body
    );
    print(response.body);
    // TODO bug: only getting post response containing every second item in the json list
    schedules=(jsonDecode(response.body) as List).map((i) =>  
      Schedule.fromJson(i)).toList();
      print(schedules.length);
      var index = 0;
      while(index < schedules.length){
        if (schedules[index].date != null) {
        String? curDate =schedules[index].date;
        String? curStime = schedules[index].startTime;
        var curDuration = int.parse(schedules[index].duration.toString());
        String dt1 = '$curDate $curStime';
        print(dt1);
        DateTime dt = DateTime.parse(dt1);
        print(dt);
        appointments.add(Appointment(
          startTime: dt,
          endTime: dt.add(Duration(hours:curDuration)),
          startTimeZone: "AUS Eastern Standard Time",
	        endTimeZone: "AUS Eastern Standard Time",  
            )
          );
        }
        index = index + 1;
    }
    return appointments;
  }

}



class _AppointmentDataSource extends CalendarDataSource {
  _AppointmentDataSource(List<Appointment> source){
   appointments = source; 
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

  readEmailStorage() async {
    emailController.text = await credentialStorage.read(key: "Key_email") ?? '';
  }

  Schedule schedule = Schedule();
  

  Future dummySchedule() async{
        // temp fix for response: sent a dummy empty entry and make sure the 2nd entry is read from response
    Map data2 ={
      'email' : schedule.email,
      'date' :  "",
      'startTime' : "",
      'duration' : "",
    };

    
    Uri url2 = Uri.parse('${baseUrl}schedule/addSchedule');
    var body2 = jsonEncode(data2);
    try {
      await http.post(
        url2,
        headers: headers,
        body: body2,
      );
    }
    catch (e) {
      print(e);
    }
    

  }
  Future createSchedule() async{

    // actual implementation
    Map data ={
      'email' : schedule.email,
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
  //masking for the dates and time 
  var maskFormatterDate = new MaskTextInputFormatter(
    mask: '####-##-##', 
    filter: { "#": RegExp(r'[0-9]') },
    type: MaskAutoCompletionType.lazy
  );

  var maskFormatterTime = new MaskTextInputFormatter(
    mask: '##:##', 
    filter: { "#": RegExp(r'[0-9]') },
    type: MaskAutoCompletionType.lazy
  );
  
  @override
  Widget build(BuildContext context) {
    readEmailStorage();
    // Build a Form widget using the _formKey created above.
    return Form(
      key: scheduleformKey,
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: <Widget>[
          TextFormField(  
            decoration: const InputDecoration(
            icon: Icon(Icons.date_range),
            hintText: 'Year-Month-Day',
            labelText: 'Date',
            ),
            inputFormatters: [
              maskFormatterDate
            ],
            onChanged: (value) {
              schedule.date = value;
            },
          ),
          TextFormField(  
            decoration: const InputDecoration(
            icon: Icon(Icons.check_circle),
            hintText: 'Hour:Minute',
            labelText: 'Start time',
            ),
            inputFormatters: [
              maskFormatterTime
            ],
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
            inputFormatters: [
              FilteringTextInputFormatter.allow(RegExp("[0-9]+"))
            ],
            onChanged: (value) {
            schedule.duration = value;
            schedule.email = emailController.text;
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
                dummySchedule();
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