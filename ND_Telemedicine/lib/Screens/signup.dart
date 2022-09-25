import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:nd_telemedicine/Globals/appbar.dart';
import 'package:nd_telemedicine/Globals/variables.dart';

import '../Globals/footer.dart';
import '../Models/user.dart';

class SignUpPage extends StatelessWidget {
  const SignUpPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    return const Scaffold(

      //Top bar/header
      appBar: DefaultTopNav(),

      // main content
      body: SignUpForm(),

      //Footer
      bottomNavigationBar: Footer(),

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

      // print(results.body);
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
      'email': user.email,
      'allergies': 'None',
      'status': 'None',
    };

    Uri url = Uri.parse('${baseUrl}record/add');
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

    Uri url = Uri.parse('${baseUrl}form/signup');
    var body = jsonEncode(data);

    try {
      await http.post(
        url,
        headers: headers,
        body: body,
      );

      // if (!mounted) return;
      Navigator.popAndPushNamed(context, '/landing'); // works we do it the 'correct' way later
    } catch (e) {
      print(e);
    }
  }



  // regular expression, tested using https://regexr.com/
  final emailRegex = RegExp(r"^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+");

  // will cover most names. rare names such as X Æ A-12(elon musk's son) will not be covered
  final nameRegex = RegExp(r"^([a-zA-Z,.'-])+$");


  //Variables for Gender dropdown menu
  List<String> gender = ['None', 'Male', 'Female', 'Others'];

  @override
  void initState() {
    super.initState();
    user.gender = gender.first;
  }

  @override
  Widget build(BuildContext context) {

    return Center(
      child: Container(
        height: 620.0,
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
                  'Fill out the form to create a account',

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

                        fields('First Name:'),


                        SizedBox(
                          width: 300.0,

                          child: TextFormField(
                            decoration: inputDeco('Enter your first name'),

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

                        fields('Surname:'),


                        SizedBox(
                          width: 300.0,

                          child: TextFormField(
                            decoration: inputDeco('Enter your surname'),

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

                        fields('Gender:'),


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

                        fields('Email:'),


                        SizedBox(
                          width: 300.0,

                          child: TextFormField(
                            decoration: inputDeco('Enter your email'),

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

                        fields('Password:'),


                        SizedBox(
                          width: 300.0,

                          child: TextFormField(
                            obscureText: true,

                            decoration: inputDeco('Enter a password'),

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
                                Navigator.popAndPushNamed(context, '/landing');
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
                                  await save();

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

SizedBox fields(String name){
  return SizedBox(
    width: 100.0,

    child: Align(
      alignment: Alignment.centerRight,

      child: Padding(
          padding: const EdgeInsets.only(right: 10.0),

          child: fieldName(name),
      ),
    ),
  );
}

Text fieldName(String name) {
  return Text(
    name,

    style: const TextStyle(
      fontFamily: 'Arvo',
      color: Colors.black,
      fontSize: 16.0,
    ),
  );
}

InputDecoration inputDeco(String hint) {
  return InputDecoration(
    border: const OutlineInputBorder(),
    hintText: hint,
  );
}
