import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:nd_telemedicine/Globals/variables.dart';
import 'package:nd_telemedicine/Models/LoginCredentials.dart';


import 'package:nd_telemedicine/Globals/footer.dart';
import 'package:nd_telemedicine/Globals/appbar.dart';

class LandingPage extends StatefulWidget {
  const LandingPage({Key? key}) : super(key: key);

  @override
  State<LandingPage> createState() => LandingPageState();
}

class LandingPageState extends State<LandingPage> {

  @override
  Widget build(BuildContext context) {
    return const Scaffold(

      //Top bar/header
      appBar: DefaultTopNav(),

      // main content
      body: LoginForm(),

      //Footer
      bottomNavigationBar: Footer(),

    );
  }
}

class LoginForm extends StatefulWidget {
  const LoginForm({super.key});

  @override
  LoginFormState createState() {
    return LoginFormState();
  }
}

class LoginFormState extends State<LoginForm> {
  LoginCredentials loginCredentials= LoginCredentials('', '', '');
  final accountFormKey = GlobalKey<FormState>();
  String emailValid = "default email valid";
  String passwordValid = "default password valid";

  //regular expression
  final emailExp = RegExp(r'/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/');
  final emailRegex = RegExp(r"^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+");

  // this is more of sorta like a workaround rather than actual proper way
  // http post body can easily be converted to int or string,
  // not sure if it can be converted to boolean(which is what i want)
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

      emailValid = results.body;
    } catch(e) {
      print(e);

      emailValid = 'false';
    }
  }

  Future login(String email, String password) async{
    http.Response results;

    Map data = {
      'email': email,
      'password': password,
    };

    Uri url = Uri.parse("${baseUrl}form/login");
    String body = jsonEncode(data);

    try {
      results = await http.post(
        url,
        headers: headers,
        body: body,
      );

      if ((results.body).isEmpty) {

        passwordValid = 'false';
      } else {

        passwordValid = 'true';
        loginCredentials.role = results.body;
      }
    } catch(e) {
      print(e);

      passwordValid = 'false';
    }
  }


  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        height: 350.0,
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

              margin: bottomMargin(),

              child: const Align(
                alignment: Alignment.bottomLeft,

                child: Text(
                  'WELCOME',

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
                  'Fill out the form to login',

                  style: TextStyle(
                    fontFamily: 'Arvo',
                    color: Colors.black,
                    fontSize: 14.0,
                  ),
                ),
              ),
            ),

            Form(
              key: accountFormKey,

              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,

                children: [

                  //Email Field
                  Container(
                    margin: bottomMargin(),

                    child: Row(
                      children: [

                        SizedBox(
                          width: 100.0,

                          child: Align(
                            alignment: fieldAlignments(),

                            child: Padding(
                              padding: rightPadding(),

                              child: fieldName('Email'),
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
                              loginCredentials.email = value;
                            },

                            validator: (value) {
                              if (value == null || value.isEmpty) {
                                return 'This field is required';
                              } else if (!emailRegex.hasMatch(value)) {
                                return 'This email is invalid';
                              } else {
                                if(emailValid == 'true') {
                                  return 'This email is not registered';
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

                         SizedBox(
                          width: 100.0,

                          child: Align(
                            alignment: fieldAlignments(),

                            child: Padding(
                              padding: rightPadding(),

                              child: fieldName('Password'),
                            ),
                          ),
                        ),


                        SizedBox(
                          width: 300.0,

                          child: TextFormField(
                            obscureText: true,

                            decoration: const InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: 'Enter your password',
                            ),

                            onChanged: (value) {
                              loginCredentials.password = value;
                            },

                            validator: (value) {
                              if (value == null || value.isEmpty) {
                                return 'This field is required';
                              } else if(value.length < 8) {
                                return 'A password is 8 characters long';
                              } else {
                                if(emailValid == 'true') {
                                  return 'The email or password entered is incorrect';
                                } else {
                                  if(passwordValid == 'false') {
                                    return 'The password is incorrect';
                                  } else {
                                    return null;
                                  }
                                }
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

                        //Login Button
                        Container(
                          margin: const EdgeInsets.only(right: 2.0),

                          child: Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 10.0, vertical: 6.0),

                            child: ElevatedButton(
                              //button text
                              child: const Text('login'),

                              //button logic/functionality when pressed
                              onPressed: () async {
                                // check if email is registered
                                await checkEmail(loginCredentials.email);

                                // if email exist/is already registered check password is correct
                                if (emailValid == 'false') {
                                  await login(loginCredentials.email, loginCredentials.password);
                                }

                                if (accountFormKey.currentState!.validate()) {
                                  await writeToStorage(loginCredentials.email, loginCredentials.password, loginCredentials.role);
                                  Navigator.popAndPushNamed(context, '/home');
                                }
                              },
                            ),
                          ),
                        ),

                        //Sigh Up Button
                        Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 10.0, vertical: 6.0),

                          child: OutlinedButton(

                            style: OutlinedButton.styleFrom(
                              side: const BorderSide(color: Colors.blue, width: 1), //<-- SEE HERE
                            ),

                            //button text
                            child: const Text(
                              'Sign Up',

                              style: TextStyle(
                                color: Colors.blue,
                              ),
                            ),

                            //button logic/functionality when pressed
                            onPressed: () {
                              Navigator.popAndPushNamed(context, '/signup');
                            },

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

writeToStorage(String email, String password, String role) async {
  await credentialStorage.write(key: 'Key_email', value: email);
  await credentialStorage.write(key: 'Key_password', value: password);
  await credentialStorage.write(key: 'Key_role', value: role);
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

EdgeInsets rightPadding() {
  return const EdgeInsets.only(right: 10.0);
}

EdgeInsets bottomMargin() {
  return const EdgeInsets.only(bottom: 10.0);
}

Alignment fieldAlignments() {
  return Alignment.centerRight;
}