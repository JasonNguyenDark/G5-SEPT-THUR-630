import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:nd_telemedicine/Globals/variables.dart';

class LandingPage extends StatefulWidget {
  const LandingPage({Key? key}) : super(key: key);

  @override
  State<LandingPage> createState() => LandingPageState();
}

class LandingPageState extends State<LandingPage> {

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
      body: const LoginForm(),

      //Footer
      bottomNavigationBar: const Footer(),

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
  final accountFormKey = GlobalKey<FormState>();

  //regular expression
  final emailExp = RegExp(r'/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/');

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

              margin: const EdgeInsets.only(bottom: 5.0),

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
              key: accountFormKey,

              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,

                children: [

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
                              } else if (!emailExp.hasMatch(value)) {
                                return 'This email is invalid';
                              } else {
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

                        //Login Button
                        Container(
                          margin: const EdgeInsets.only(right: 2.0),

                          child: Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 10.0, vertical: 6.0),

                            child: ElevatedButton(
                              //button text
                              child: const Text('login'),

                              //button logic/functionality when pressed
                              onPressed: () {
                                if (accountFormKey.currentState!.validate()) {
                                  ScaffoldMessenger.of(context).showSnackBar(
                                    const SnackBar(content: Text('Processing Data')),
                                  );
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
                              Navigator.pushNamed(context, '/signup');
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

