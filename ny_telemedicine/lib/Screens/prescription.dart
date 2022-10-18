import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:ny_telemedicine/Globals/variables.dart';
import 'package:ny_telemedicine/Models/LoginCredentials.dart';

import 'package:intl/intl.dart';
import 'package:datetime_picker_formfield/datetime_picker_formfield.dart';

import 'package:ny_telemedicine/Globals/footer.dart';
import 'package:ny_telemedicine/Globals/appbar.dart';
import 'package:ny_telemedicine/Models/Prescription.dart';


class PrescriptionPage extends StatefulWidget {
  const PrescriptionPage({Key? key}) : super(key: key);

  @override
  State<PrescriptionPage> createState() => PrescriptionPageState();
}

class PrescriptionPageState extends State<PrescriptionPage> {

  @override
  Widget build(BuildContext context) {
    return  Scaffold(

      //Top bar/header
      appBar: GlobalNavBar(),

      body: const PrescriptionForm(),

      //Footer
      bottomNavigationBar: const Footer(),

    );
  }
}

class PrescriptionForm extends StatefulWidget {
  const PrescriptionForm({super.key});

  @override
  State<PrescriptionForm> createState() => PrescriptionFormState();
}

class PrescriptionFormState extends State<PrescriptionForm> {

  final prescriptionFormKey = GlobalKey<FormState>();

  Prescription prescription = Prescription('', '', '', '', '', DateTime.now());

  final dateFormat = DateFormat("yyyy-MM-dd");

  final emailRegex = RegExp(r"^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+");

  final fullNameRegex = RegExp(r"^([a-zA-Z,.'-]+\s{1}[a-zA-Z,.'-]+)$");

  Future save() async {
    Map data = {
      'patientEmail': prescription.patientEmail,
      'patientName': prescription.patientName,
      'doctorName': prescription.doctorName,
      'medicine': prescription.medicine,
      'date': prescription.date,
      'description': prescription.description,
    };

    Uri url = Uri.parse('${prescriptionUrl}prescription/add');

    String body = jsonEncode(data);

    try {
      await http.post(
        url,
        headers: headers,
        body: body,
      );
    } catch(e) {
      print(e);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(

      children: [

        Container(
          margin: const EdgeInsets.only(top: 20.0),

          child: Column(

            children: [
              Form(
                key: prescriptionFormKey,

                child: Column(

                  children: [

                    // date format is yyyy-MM-dd
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,

                      children: [
                        field('Date(yyyy-MM-dd)'),

                        Container(
                          width: 250.0,
                          height: 50.0,

                          padding: const EdgeInsets.only(left: 10.0),

                          decoration: inputBorder(),

                          child: DateTimeField(
                            format: dateFormat,

                            decoration: inputDeco('Select date'),

                            style: inputStyle(),

                            onShowPicker: (context, currentValue) {
                              return showDatePicker(
                                context: context,
                                firstDate: DateTime.now(),
                                initialDate: currentValue ?? DateTime.now(),
                                lastDate: DateTime(2100),
                              );
                            },

                            onChanged: (value) {
                              prescription.date = value!;
                            },

                            validator: (value) {
                              if(value == null) {
                                return 'This field is required';
                              } else {
                                return null;
                              }
                            },
                          ),
                        ),
                      ],
                    ),

                    // patient emails
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,

                      children: [

                        field('Patient Email'),

                        Container(
                          width: 250.0,
                          height: 50.0,
                          padding: const EdgeInsets.only(left: 10.0),

                          decoration: inputBorder(),

                          child: TextFormField(
                            decoration: inputDeco("Enter patient's email"),

                            style: inputStyle(),

                            onChanged: (value) {
                              prescription.patientEmail = value;
                            },

                            validator: (value) {
                              if(value == null || value.isEmpty) {
                                return 'This field is required';
                              } else if (!emailRegex.hasMatch(value)) {
                                return 'The email provided is invalid';
                              } else {
                                return null;
                              }
                            },
                          ),
                        ),

                      ],
                    ),

                    // patient full name excluding middle name
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,

                      children: [

                        field('Patient Name'),

                        Container(
                          width: 250.0,
                          height: 50.0,
                          padding: const EdgeInsets.only(left: 10.0),

                          decoration: inputBorder(),

                          child: TextFormField(
                            decoration: inputDeco("Enter patient's first name and surname"),

                            style: inputStyle(),

                            onChanged: (value) {
                              prescription.patientName = value;
                            },

                            validator: (value) {
                              if(value == null || value.isEmpty) {
                                return 'This field is required';
                              } else if(!fullNameRegex.hasMatch(value)) {
                                return 'The patient name provided is invalid';
                              } else {
                                return null;
                              }
                            },
                          ),
                        ),

                      ],
                    ),

                    // Doctor full name excluding middle name
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,

                      children: [

                        field('Doctor Name'),

                        Container(
                          width: 250.0,
                          height: 50.0,
                          padding: const EdgeInsets.only(left: 10.0),

                          decoration: inputBorder(),

                          child: TextFormField(
                            decoration: inputDeco("Enter doctor's first name and surname"),

                            style: inputStyle(),

                            onChanged: (value) {
                              prescription.doctorName = value;
                            },

                            validator: (value) {
                              if(value == null || value.isEmpty) {
                                return 'This field is required';
                              } else if(!fullNameRegex.hasMatch(value)) {
                                return 'The doctor name provided is invalid';
                              } else {
                                return null;
                              }
                            },
                          ),
                        ),

                      ],
                    ),

                    // medicine
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.center,

                      children: [

                        field('Medicines'),

                        Container(
                          width: 250.0,
                          height: 50.0,
                          padding: const EdgeInsets.only(left: 10.0),

                          decoration: inputBorder(),

                          child: TextFormField(
                            decoration: inputDeco("Add medicines"),

                            style: inputStyle(),

                            onChanged: (value) {
                              prescription.medicine = value;
                            },

                            validator: (value) {
                              if(value == null || value.isEmpty) {
                                return 'This field is required';
                              } else {
                                return null;
                              }
                            },
                          ),
                        ),

                      ],
                    ),

                    // Description/Usage basically how to use the medicine and/or dosage
                    Container(
                      height: 50.0,
                      width: 400.0,
                      padding: const EdgeInsets.only(left: 10.0),

                      alignment: Alignment.centerLeft,
                      decoration: BoxDecoration(
                        color: Colors.grey,
                        border: Border.all(width: 1.0),
                      ),

                      child: const Text(
                        'Description/Usage',

                        style: TextStyle(
                          fontFamily: 'Arvo',
                          color: Colors.black,
                          fontSize: 16.0,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),

                    // input for description
                    Container(
                      width:400.0,

                      decoration: BoxDecoration(
                        border: Border.all(width: 1.0),
                      ),

                      child: TextFormField(
                        decoration: inputDeco("Add medicines"),

                        style: inputStyle(),

                        onChanged: (value) {
                          prescription.description = value;
                        },

                        validator: (value) {
                          if(value == null || value.isEmpty) {
                            return 'This field is required';
                          } else {
                            return null;
                          }
                        },
                      ),
                    ),

                    Container(
                      margin: const EdgeInsets.only(top:10.0),

                      child:Padding(
                        padding: const EdgeInsets.symmetric(
                            horizontal: 10.0, vertical: 6.0),
                        child: ElevatedButton(
                          //button text
                          child: const Text('Submit'),

                          //button logic/functionality when pressed
                          onPressed: () async {
                            if(prescriptionFormKey.currentState!.validate()) {
                              await save();

                              // reloads page
                              Navigator.popAndPushNamed(context, '/prescription');
                            }
                          },
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}

Container field(String fieldName) {
  return Container(
    alignment: Alignment.center,
    height: 50.0,
    width: 150.0,

    decoration: const BoxDecoration(
      color: Colors.grey,
      border: Border(
        top: BorderSide(width: 1.0),
        left: BorderSide(width: 1.0),
        right: BorderSide(width: 1.0),
      ),
    ),

    child: Text(
      fieldName,

      style: const TextStyle(
        fontFamily: 'Arvo',
        color: Colors.black,
        fontSize: 16.0,
        fontWeight: FontWeight.bold,
      ),
    ),
  );
}

InputDecoration inputDeco(String hint) {
  return InputDecoration(
    border: InputBorder.none,
    focusedBorder: InputBorder.none,
    enabledBorder: InputBorder.none,
    errorBorder: InputBorder.none,
    disabledBorder: InputBorder.none,

    errorStyle: const TextStyle(fontSize: 9, height: 0.3),
    hintText: hint,
  );
}

TextStyle inputStyle() {
  return const TextStyle(
    fontFamily: 'Arvo',
    color: Colors.black,
    fontSize: 10.0,
  );
}

BoxDecoration inputBorder() {
  return const BoxDecoration(
    border: Border(
      top: BorderSide(width: 1.0),
      right: BorderSide(width: 1.0),

    ),
  );
}