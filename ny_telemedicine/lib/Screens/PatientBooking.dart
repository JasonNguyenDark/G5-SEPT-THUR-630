import 'dart:async';
import 'dart:convert';
// import 'dart:html';
// import 'dart:io';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:ny_telemedicine/Globals/appbar.dart';
import 'package:ny_telemedicine/Globals/variables.dart';
import 'package:syncfusion_flutter_calendar/calendar.dart';
import 'package:mask_text_input_formatter/mask_text_input_formatter.dart';
import '../Models/schedule.dart';
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
      appBar: GlobalNavBar(),

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

  String selected = "Choose your booking";
  @override
  void initState(){
    super.initState();
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
                  // print(snapshot2.data);
                  if (snapshot2.data != null) {
                    if(bookable == true){
                      return SafeArea(
                          child:Column(
                              children:[
                                Center(
                                    child:DropdownButton<String>(
                                      hint: Text(selected),
                                      items: snapshot2.data.map<DropdownMenuItem<String>>((item) {
                                        return DropdownMenuItem<String>(
                                          value: item,
                                          child: Text(item),
                                        );
                                      }).toList(),
                                      onChanged: (value) {
                                        setState(() {
                                          selected = value!;
                                        });
                                      },
                                    )
                                ),
                                Center(
                                    child:ElevatedButton.icon(
                                      onPressed: () async {
                                        await Future.delayed(Duration(seconds: 5));
                                        sentBooking(selected);
                                        Navigator.pushNamed(context, '/booking');
                                      },
                                      icon: Icon(Icons.add, size: 18),
                                      label: Text("BOOK"),
                                    )
                                )
                              ]
                          )
                      );

                    }
                    else{
                      return Container(
                        child: Center(
                          child: Text('Already Booked'),
                        ),
                      );
                    }
                  } else {
                    return Container(
                      child: Center(
                        child: Text('loading...'),
                      ),
                    );
                  }

                }
            )
          ]
      ),
    );
  }

  Future sentBooking(String choice) async{
    var parts = choice.split(' ');
    Map data ={
      'id':parts[0],
      'email': emailController.text,
    };
    print(data);

    Uri url = Uri.parse('${baseUrl}schedule/updateById');
    var body = jsonEncode(data);
    try {
      await http.put(
        url,
        headers: headers,
        body: body,
      );
    }
    catch (e) {
      print(e);
    }

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

  Future <List> getBookable() async{
    readEmailStorage();
    await Future.delayed(Duration(seconds: 2));
    List<Schedule> schedules;
    List<String> returned = [];
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
      int? curId = schedules[index].id;
      String? name = schedules[index].email;
      String? date = schedules[index].date;
      String? stime = schedules[index].startTime;
      String? duration = schedules[index].duration.toString();

      String detail ="$curId $name $date $stime $duration" + " hours";
      returned.add(detail);
      index = index + 1;
    }
    return returned;
  }

}

class _AppointmentDataSource extends CalendarDataSource {
  _AppointmentDataSource(List<Appointment> source){
    appointments = source;
  }
}






