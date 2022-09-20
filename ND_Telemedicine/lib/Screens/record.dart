import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:nd_telemedicine/Globals/variables.dart';
import 'package:nd_telemedicine/Models/Record.dart';

import 'package:nd_telemedicine/Globals/footer.dart';
import 'package:nd_telemedicine/Globals/appbar.dart';

class RecordPage extends StatefulWidget {
  const RecordPage({Key? key}) : super(key: key);

  @override
  State<RecordPage> createState() => RecordPageState();
}

class RecordPageState extends State<RecordPage> {

  @override
  Widget build(BuildContext context) {

    return Scaffold(

      //Top bar/header
      appBar: GlobalNavBar(),

      body: const Body(),
      //Footer
      bottomNavigationBar: const Footer(),

    );
  }
}

class Body extends StatefulWidget {
  const Body({Key? key}) : super(key: key);

  @override
  State<Body> createState() => BodyState();
}

class BodyState extends State<Body> {
  // final TextEditingController storageEmail = TextEditingController(text: "");
  // String role = '';

  final TextEditingController allergiesController = TextEditingController(text: '');
  final TextEditingController statusController = TextEditingController(text: '');

  Record rec = Record('', '', '', '', '', '');

  final allergiesKey = GlobalKey<FormState>();
  final statusKey = GlobalKey<FormState>();
  bool allergiesStatus = false;
  bool symptomStatus = false;

  Future<void> readEmailFromStorage() async {
    String email = await credentialStorage.read(key: "Key_email") ?? '';

    http.Response response;
    Map data = {
      'email' : email,
    };

    Uri url = Uri.parse("${baseUrl}record/get/record");
    String body = jsonEncode(data);

    response = await http.post(
      url,
      headers: headers,
      body: body,
    );

    var decoder = jsonDecode(response.body);

    // these essentially store the initial values and cannot be changed without updating tables
    rec.name = decoder['name'];
    rec.surname = decoder['surname'];
    rec.gender = decoder['gender'];
    rec.email = decoder['email'];
    rec.status = decoder['status'];
    rec.allergies = decoder['allergies'];

     // stores the changed values of allergies and status
    allergiesController.text = decoder['allergies'];
    statusController.text = decoder['status'];

  }

  Future updateAllergies() async {
    Map data = {
      'email' : rec.email,
      'allergies': allergiesController.text,
    };

    Uri url = Uri.parse("${baseUrl}record/update/allergies");
    String body = jsonEncode(data);

    await http.put(
      url,
      headers: headers,
      body: body,
    );
  }

  Future updateStatus() async {
    Map data = {
      'email' : rec.email,
      'status': statusController.text,
    };

    Uri url = Uri.parse("${baseUrl}record/update/status");
    String body = jsonEncode(data);

    await http.put(
      url,
      headers: headers,
      body: body,
    );
  }

  @override
  void dispose() {
    // Clean up the controller when the widget is removed from the widget tree.
    // This also removes the _printLatestValue listener.
    allergiesController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<void>(
        future: readEmailFromStorage(),

        builder: (BuildContext context, AsyncSnapshot<void> snapshot) {
          return Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  // firstname surname field
                  Row(
                    children: [
                      // first name
                      Container(
                        width: 400.0,
                        margin:
                            const EdgeInsets.only(right: 100.0, bottom: 50.0),
                        decoration: fieldBoxDecoration(),
                        child: Row(
                          children: [
                            fields('FIRST NAME:'),
                            userInfo(rec.name)],
                        ),
                      ),

                      //surname
                      Container(
                        width: 400.0,
                        margin: const EdgeInsets.only(bottom: 50.0),
                        decoration: fieldBoxDecoration(),
                        child: Row(
                          children: [
                            fields('SURNAME:'),
                            userInfo(rec.surname)],
                        ),
                      ),
                    ],
                  ),

                  //gender
                  Row(
                    children: [
                      // Gender
                      Container(
                        width: 400.0,
                        margin:
                            const EdgeInsets.only(right: 100.0, bottom: 50.0),
                        decoration: fieldBoxDecoration(),
                        child: Row(
                          children: [
                            fields('GENDER:'),
                            userInfo(rec.gender),
                          ],
                        ),
                      ),
                    ],
                  ),

                  //Allergies and symptom
                  Row(
                    children: [
                      // Allergies
                      Form(
                        key: allergiesKey,
                        child: Container(
                          margin: const EdgeInsets.only(right: 100.0),
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.start,
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              //Field name
                              formFields('ALLERGIES'),

                              //Input field by default user cant use it
                              Container(
                                width: 400.0,
                                decoration: formBoxDecoration(),
                                child: TextFormField(
                                  enabled: allergiesStatus,
                                  maxLines: 5,

                                  controller: allergiesController,

                                  inputFormatters: [
                                    LengthLimitingTextInputFormatter(200),
                                  ],
                                  decoration: formInputDeco(),
                                  style: formTextStyle(),
                                ),
                              ),

                              //Button
                              Container(
                                margin: const EdgeInsets.only(top: 15.0),
                                child: Row(
                                  children: [
                                    //edit and cancel button
                                    Container(
                                      margin:
                                          const EdgeInsets.only(right: 10.0),
                                      child: ElevatedButton(
                                        child: buttonText(allergiesStatus),
                                        onPressed: () {
                                          setState(() {
                                            if (allergiesStatus == false) {
                                              allergiesStatus = true;
                                            } else {
                                              allergiesStatus = false;
                                            }
                                          });
                                        },
                                      ),
                                    ),

                                    Visibility(
                                      visible: allergiesStatus,
                                      child: ElevatedButton(
                                        child: const Text('Save'),
                                        onPressed: () async {
                                          // if the new values(with all the whitespace removed)
                                          // is the same as initial value DONT update database, its pointless to do so
                                          if((allergiesController.text).replaceAll(' ', '') != rec.allergies) {
                                            await updateAllergies();
                                          }

                                          setState(() {
                                            allergiesStatus = false;
                                          });
                                        },
                                      ),
                                    ),
                                  ],
                                ),
                              ),
                            ],
                          ),
                        ),
                      ),

                      Form(
                        key: statusKey,
                        child: Column(
                          children: [
                            //Field name
                            formFields('STATUS/SYMPTOMS'),

                            //Input field by default user cant use it
                            Container(
                              width: 400.0,
                              decoration: formBoxDecoration(),
                              child: TextFormField(
                                enabled: symptomStatus,
                                maxLines: 5,
                                controller: statusController,
                                inputFormatters: [
                                  LengthLimitingTextInputFormatter(200),
                                ],
                                decoration: formInputDeco(),
                                style: formTextStyle(),
                              ),
                            ),

                            //Button
                            Container(
                              margin: const EdgeInsets.only(top: 15.0),
                              width: 400.0,
                              child: Row(
                                children: [
                                  //edit and cancel button
                                  Container(
                                    margin: const EdgeInsets.only(right: 10.0),
                                    child: ElevatedButton(
                                      child: buttonText(symptomStatus),
                                      onPressed: () {
                                        setState(() {
                                          if (symptomStatus == false) {
                                            symptomStatus = true;
                                          } else {
                                            symptomStatus = false;
                                          }
                                        });
                                      },
                                    ),
                                  ),

                                  Visibility(
                                    visible: symptomStatus,
                                    child: ElevatedButton(
                                      child: const Text('Save'),
                                      onPressed: () async {
                                        if((statusController.text).replaceAll(' ', '') != rec.status) {
                                          await updateStatus();
                                        }

                                        setState(() {
                                          symptomStatus= false;
                                        });
                                      },
                                    ),
                                  ),

                                  //Save button
                                ],
                              ),
                            ),
                          ],
                        ),
                      ),
                    ],
                  ),

                  //prescription
                  // will add functionality once we get here for now just display
                  Container(
                    width: 400.0,
                    margin: const EdgeInsets.only(bottom: 50.0, top: 50.0),
                    decoration: fieldBoxDecoration(),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: const [
                        Padding(
                          padding: EdgeInsets.only(bottom: 10.0),
                          child: Text(
                            'Prescription',
                            style: TextStyle(
                              fontFamily: 'Arvo',
                              color: Colors.black,
                              fontSize: 38.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              )
            ],
          );
        });
  }
}

Padding fields(String name) {
  return Padding(
    padding: const EdgeInsets.only(right: 10.0),

    child: Text(
      name,

      style: const TextStyle(
        fontFamily: 'Arvo',
        color: Colors.black,
        fontSize: 38.0,
        fontWeight: FontWeight.bold,
      ),
    ),
  );
}

Text userInfo(String info) {
  return Text(
    info,

    style: const TextStyle(
      fontFamily: 'Arvo',
      color: Colors.black,
      fontSize: 38.0,
    ),
  );
}

BoxDecoration fieldBoxDecoration() {
  return const BoxDecoration(
      border: Border(
        bottom: BorderSide(color: Colors.black),
      )
  );
}

BoxDecoration formBoxDecoration() {
  return BoxDecoration(
      border: Border.all(color: Colors.black),
  );
}

Text buttonText(bool status) {
  return Text(() {
    if(status == true) {
      return 'Cancel';
    } else {
      return 'Edit';
    }
  }());
}

// Allergies and symptoms design

Container formFields(String field) {
  return Container(
    width: 400.0,
    margin: const EdgeInsets.only(bottom: 10.0),

    decoration: fieldBoxDecoration(),

    child: fields(field),
  );
}

InputDecoration formInputDeco() {
  return const InputDecoration(
    border: InputBorder.none,
    focusedBorder: InputBorder.none,
    enabledBorder: InputBorder.none,
    errorBorder: InputBorder.none,
    disabledBorder: InputBorder.none,
  );
}

TextStyle formTextStyle() {
  return const TextStyle(
    fontSize: 16,
    fontFamily: 'Arvo',
  );
}