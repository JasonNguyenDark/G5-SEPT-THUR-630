import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';


// import 'package:dob_input_field/dob_input_field.dart'; ignore this for now

import 'package:http/http.dart' as http;
import 'package:nd_telemedicine/Globals/variables.dart';

import '../Models/user.dart';
import '../Models/Record.dart';

class SignUpPage extends StatelessWidget {
  const SignUpPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    return Scaffold(

      //Top bar/header
      appBar: AppBar(
        backgroundColor: Colors.blue,

        title: const Text(
          'NY TELEMEDICINE',

          //all styling goes here
          style: TextStyle(
            fontFamily: 'Arvo',
            color: Colors.black,
            fontSize: 36.0,
            fontWeight: FontWeight.bold,
          ),
        ),
      ),

      // main content
      body: const SignUpForm(),

      //Footer
      bottomNavigationBar: const Footer(),

    );
  }
}

class SignUpForm extends StatefulWidget {
  const SignUpForm({super.key});

  @override
  SignUpFormState createState() {
    return SignUpFormState();
  }
}

class SignUpFormState extends State<SignUpForm> {
  final signUpFormKey = GlobalKey<FormState>();

  User user = User('', '', '', '', '');
  String emailValid = "default email valid";

  Future checkEmail(String email) async{
    http.Response results;

    Map data = {
      'email': email,
    };

    Uri url = Uri.parse("${baseUrl}form/checkEmail");
    String body = jsonEncode(data);

    try {
      results = await http.post(
        url,
        headers: headers,
        body: body,
      );

      print(results.body);
      emailValid = results.body;
    } catch(e) {
      print(e);

      emailValid = 'true';
    }
  }

  Future createRecord() async {
    Map data ={
      'name': user.name,
      'surname': user.surname,
      'gender': user.gender,
      'allergies': 'None',
      'status': 'None',
    };

    Uri url = Uri.parse('${baseUrl}record/addRecord');
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

  Future save() async {
    Map data ={
      'name': user.name,
      'surname': user.surname,
      'gender': user.gender,
      'email': user.email,
      'password': user.password,
    };

    // Uri url = Uri.parse('${baseUrl}form/add');
    Uri url = Uri.parse('${baseUrl}form/signup');
    var body = jsonEncode(data);

    try {
      await http.post(
        url,
        headers: headers,
        body: body,
      );

      // if (!mounted) return;
      Navigator.of(context).pop(); // works we do it the 'correct' way later
    } catch (e) {
      print(e);
    }
  }



  //regular expression, tested using https://regexr.com/
  // both email regex works
  final emailRegex = RegExp(r"^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+");
  // final emailExp = RegExp(r"^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$");

  // will cover most names. rare names such as X Æ A-12(elon musk's son) will not be covered
  final nameRegex = RegExp(r"^([a-zA-Z,.'-])+$");


  //Variables for Gender dropdown menu
  List<String> gender = ['None', 'Male', 'Female', 'Others'];

  @override
  Widget build(BuildContext context) {
    user.gender = gender.first;
    return Center(
      child: Container(
        height: 700.0,
        width: 500.0,

        padding: const EdgeInsets.all(20.0),
        margin: const EdgeInsets.only(left: 20.0, right: 20.0),

        decoration: BoxDecoration(
            border: Border.all(color: Colors.blueAccent)
        ),

        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisSize: MainAxisSize.min,

          children: [

            //Header/Title of the form
            Container(
              height: 50.0,

              margin: const EdgeInsets.only(bottom: 5.0),

              child: const Align(
                alignment: Alignment.bottomLeft,

                child: Text(
                  'Sign Up',

                  style: TextStyle(
                    fontFamily: 'Arvo',
                    color: Colors.black,
                    fontSize: 36.0,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
            ),


            //Description. ATM Placeholder text
            Container(
              margin: const EdgeInsets.only(bottom: 10.0),

              child: const Align(
                alignment: Alignment.bottomLeft,

                child: Text(
                  'Descriptions goes here',

                  style: TextStyle(
                    fontFamily: 'Arvo',
                    color: Colors.black,
                    fontSize: 16.0,
                  ),
                ),
              ),
            ),

            Form(
              key: signUpFormKey,

              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,

                children: [

                  // First/given name Field
                  Container(
                    margin: const EdgeInsets.only(bottom: 10.0),

                    child: Row(
                      children: [

                        const SizedBox(
                          width: 100.0,

                          child: Align(
                            alignment: Alignment.centerRight,

                            child: Padding(
                              padding: EdgeInsets.only(right: 10.0),

                              child: Text(
                                'First Name:',

                                style: TextStyle(
                                  fontFamily: 'Arvo',
                                  color: Colors.black,
                                  fontSize: 16.0,
                                ),
                              ),
                            ),
                          ),
                        ),


                        SizedBox(
                          width: 300.0,

                          child: TextFormField(
                            decoration: const InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Enter your name',
                            ),

                            onChanged: (value) {
                              user.name = value;
                            },

                            validator: (value) {
                              if (value == null || value.isEmpty) {
                                return 'This field is required';
                              } else if (!nameRegex.hasMatch(value)) {
                                return 'The name provided is invalid';
                              } else {
                                return null;
                              }
                            }, //validator
                          ),
                        ),

                      ],
                    ),
                  ),

                  //Surname Field
                  Container(
                    margin: const EdgeInsets.only(bottom: 10.0),

                    child: Row(
                      children: [

                        const SizedBox(
                          width: 100.0,

                          child: Align(
                            alignment: Alignment.centerRight,

                            child: Padding(
                              padding: EdgeInsets.only(right: 10.0),

                              child: Text(
                                'Surname:',

                                style: TextStyle(
                                  fontFamily: 'Arvo',
                                  color: Colors.black,
                                  fontSize: 16.0,
                                ),
                              ),
                            ),
                          ),
                        ),


                        SizedBox(
                          width: 300.0,

                          child: TextFormField(
                            decoration: const InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Enter your surname',
                            ),

                            onChanged: (value) {
                              user.surname = value;
                            },

                            validator: (value) {
                              if (value == null || value.isEmpty) {
                                return 'This field is required';
                              } else if (!nameRegex.hasMatch(value)) {
                                return 'The surname provided is invalid';
                              } else {
                                return null;
                              }
                            }, //validator
                          ),
                        ),

                      ],
                    ),
                  ),

                  //Gender Field
                  Container(
                    margin: const EdgeInsets.only(bottom: 10.0),

                    child: Row(
                      children: [

                        const SizedBox(
                          width: 100.0,

                          child: Align(
                            alignment: Alignment.centerRight,

                            child: Padding(
                              padding: EdgeInsets.only(right: 10.0),

                              child: Text(
                                'Gender:',

                                style: TextStyle(
                                  fontFamily: 'Arvo',
                                  color: Colors.black,
                                  fontSize: 16.0,
                                ),
                              ),
                            ),
                          ),
                        ),


                        SizedBox(
                          width: 300.0,

                          child: ButtonTheme(
                            alignedDropdown: true,

                            child: DropdownButtonFormField<String> (
                              // value: genderValue,
                              value: user.gender,

                              decoration: const InputDecoration(
                                border: OutlineInputBorder(),
                              ),

                              onChanged: (String? value) {
                                // This is called when the user selects an item.
                                setState(() {
                                  user.gender = value!;
                                  // genderValue = value!;
                                });
                              },
                              items: ['None', 'Male', 'Female', 'Other'].map<DropdownMenuItem<String>>((String value) {
                                return DropdownMenuItem<String>(
                                  value: value,
                                  child: Text(value),
                                );
                              }).toList(),

                              validator: (value){
                                if(value == 'None') {
                                  return 'This field is required';
                                } else {
                                  return null;
                                }
                              },
                            ),
                          ),

                        ),

                      ],
                    ),
                  ),


                  //Email Field
                  Container(
                    margin: const EdgeInsets.only(bottom: 10.0),

                    child: Row(
                      children: [

                        const SizedBox(
                          width: 100.0,

                          child: Align(
                            alignment: Alignment.centerRight,

                            child: Padding(
                              padding: EdgeInsets.only(right: 10.0),

                              child: Text(
                                'Email:',

                                style: TextStyle(
                                  fontFamily: 'Arvo',
                                  color: Colors.black,
                                  fontSize: 16.0,
                                ),
                              ),
                            ),
                          ),
                        ),


                        SizedBox(
                          width: 300.0,

                          child: TextFormField(
                            decoration: const InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Enter your email address',
                            ),

                            onChanged: (value) {
                              user.email = value;
                            },

                            validator: (value) {
                              if (value == null || value.isEmpty) {
                                return 'This field is required';
                              } else if (!emailRegex.hasMatch(value)) {
                                return 'This email is invalid';
                              } else {
                                if(emailValid == 'false') {
                                  return 'This email is already registered';
                                } else {
                                  return null;
                                }
                              }
                            }, //validator
                          ),
                        ),

                      ],
                    ),
                  ),

                  //Password Field
                  Container(
                    margin: const EdgeInsets.only(bottom: 10.0),

                    child: Row(
                      children: [

                        const SizedBox(
                          width: 100.0,

                          child: Align(
                            alignment: Alignment.centerRight,

                            child: Padding(
                              padding: EdgeInsets.only(right: 10.0),

                              child: Text(
                                'Password:',

                                style: TextStyle(
                                  fontFamily: 'Arvo',
                                  color: Colors.black,
                                  fontSize: 16.0,
                                ),
                              ),
                            ),
                          ),
                        ),


                        SizedBox(
                          width: 300.0,

                          child: TextFormField(
                            decoration: const InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Enter your password',
                            ),

                            onChanged: (value) {
                              user.password = value;
                            },

                            validator: (value) {
                              if (value == null || value.isEmpty) {
                                return 'This field is required';
                              } else if(value.length < 8) {
                                return 'Password must be 8 characters or longer';
                              } else {
                                return null;
                              }
                            }, //validator
                          ),
                        ),

                      ],
                    ),
                  ),

                  //buttons
                  Container(
                    margin: const EdgeInsets.only(top: 15.0),
                    child: Row(
                      children: [

                        //back Button
                        Container(
                          margin: const EdgeInsets.only(right: 2.0),
                          child: Padding(
                            padding: const EdgeInsets.symmetric(
                                horizontal: 10.0, vertical: 6.0),
                            child: ElevatedButton(

                              //button text
                              child: const Text('Back'),

                              //button logic/functionality when pressed
                              onPressed: () {
                                Navigator.pop(context);
                              },
                            ),
                          ),
                        ),

                        Container(
                          margin: const EdgeInsets.only(right: 2.0),
                          child: Padding(
                            padding: const EdgeInsets.symmetric(
                                horizontal: 10.0, vertical: 6.0),
                            child: ElevatedButton(

                              //button text
                              child: const Text('Sign Up'),

                              //button logic/functionality when pressed
                              onPressed: () async {
                                await checkEmail(user.email);

                                if(signUpFormKey.currentState!.validate()) {
                                  await createRecord();
                                  save();
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
        ),
      ),
    );
  }


}

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

