import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../Globals/DataBaseFunction.dart';


import '../Models/user.dart';

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

  // i could not get this to work at all if you know how to make it work let me know
  // i was using xampp for local testing
  // - richard
  var url = Uri.parse('Insert Url of database/#/sighUp');

  Future save() async {
    print(url);
    try{
      var res = await http.post(url,
          headers: {"Content-Type": "application/json"},
          body: json.encode({'id': 1, 'email': 'test', 'password': 'test2'}));
    } catch(e) {
      print(e);
    }

    // print(res.body);
    Navigator.pop(context);
  }

  //regular expression
  final emailExp = RegExp(r'^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$');
  final nameExp = RegExp(r"^([a-zA-Z,.'-])+$");
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        height: 600.0,
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

                  //First/given name Field
                  // Container(
                  //   margin: const EdgeInsets.only(bottom: 10.0),
                  //
                  //   child: Row(
                  //     children: [
                  //
                  //       const SizedBox(
                  //         width: 100.0,
                  //
                  //         child: Align(
                  //           alignment: Alignment.centerRight,
                  //
                  //           child: Padding(
                  //             padding: EdgeInsets.only(right: 10.0),
                  //
                  //             child: Text(
                  //               'First Name:',
                  //
                  //               style: TextStyle(
                  //                 fontFamily: 'Arvo',
                  //                 color: Colors.black,
                  //                 fontSize: 16.0,
                  //               ),
                  //             ),
                  //           ),
                  //         ),
                  //       ),
                  //
                  //
                  //       SizedBox(
                  //         width: 300.0,
                  //
                  //         child: TextFormField(
                  //           decoration: const InputDecoration(
                  //             border: OutlineInputBorder(),
                  //             hintText: 'Enter your name',
                  //           ),
                  //
                  //           validator: (value) {
                  //             if (value == null || value.isEmpty) {
                  //               return 'This field is required';
                  //             } else if (!nameExp.hasMatch(value)) {
                  //               return 'The name provided is invalid';
                  //             } else {
                  //               return null;
                  //             }
                  //           }, //validator
                  //         ),
                  //       ),
                  //
                  //     ],
                  //   ),
                  // ),

                  //Surname Field
                  // Container(
                  //   margin: const EdgeInsets.only(bottom: 10.0),
                  //
                  //   child: Row(
                  //     children: [
                  //
                  //       const SizedBox(
                  //         width: 100.0,
                  //
                  //         child: Align(
                  //           alignment: Alignment.centerRight,
                  //
                  //           child: Padding(
                  //             padding: EdgeInsets.only(right: 10.0),
                  //
                  //             child: Text(
                  //               'Surname:',
                  //
                  //               style: TextStyle(
                  //                 fontFamily: 'Arvo',
                  //                 color: Colors.black,
                  //                 fontSize: 16.0,
                  //               ),
                  //             ),
                  //           ),
                  //         ),
                  //       ),
                  //
                  //
                  //       SizedBox(
                  //         width: 300.0,
                  //
                  //         child: TextFormField(
                  //           decoration: const InputDecoration(
                  //             border: OutlineInputBorder(),
                  //             hintText: 'Enter your surname',
                  //           ),
                  //
                  //           validator: (value) {
                  //             if (value == null || value.isEmpty) {
                  //               return 'This field is required';
                  //             } else if (!nameExp.hasMatch(value)) {
                  //               return 'The name provided is invalid';
                  //             } else {
                  //               return null;
                  //             }
                  //           }, //validator
                  //         ),
                  //       ),
                  //
                  //     ],
                  //   ),
                  // ),

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

                            validator: (value) {
                              if (value == null || value.isEmpty) {
                                return 'This field is required';
                              }
                              // else if (emailExp.hasMatch(value)) {
                              //   return 'This email is invalid';
                              // }
                              else {
                                return null;
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

                            validator: (value) {
                              if (value == null || value.isEmpty) {
                                return 'This field is required';
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
                              onPressed: () {
                                if(signUpFormKey.currentState!.validate()) {
                                  // DBFunctions.signUp('test', 'test2');
                                  save();
                                  // Navigator.pop(context);
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

