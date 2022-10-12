import 'dart:js';

import 'package:flutter/material.dart';
import 'package:nd_telemedicine/Globals/variables.dart';

// For landing page and sign up
class DefaultTopNav extends StatelessWidget implements PreferredSizeWidget {
  const DefaultTopNav({Key? key}) : super(key: key);


  @override
  Widget build(BuildContext context) {
    return AppBar(
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
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
}

// For screens after login
class GlobalNavBar extends StatelessWidget implements PreferredSizeWidget {
  GlobalNavBar({Key? key}) : super(key: key);

  final TextEditingController roleController = TextEditingController(text: "");

  // Read values
  Future<void> readFromStorage() async {
    roleController.text = await credentialStorage.read(key: "Key_role") ?? '';
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<void>(
        future: readFromStorage(),
        builder: (BuildContext context, AsyncSnapshot<void> snapshot) {
          return AppBar(
            actions: <Widget> [

              //HOME
              DecoratedBox(
                  decoration: const BoxDecoration(
                    border: Border(
                      left: BorderSide(color: Colors.black),
                      right: BorderSide(color: Colors.black),
                    ),
                  ),

                child: TextButton(
                  child: itemName("HOME"),
                  onPressed: () {
                    Navigator.popAndPushNamed(context, '/home');
                  },
                ),
              ),

              //PROFILE
              DecoratedBox(
                decoration: BoxDecoration(
                  border: rightBorder(),
                ),

                child: TextButton(
                  child: itemName("PROFILE"),
                  onPressed: () {
                    Navigator.popAndPushNamed(context, '/profile');
                  },
                ),
              ),

              //add account/ this nav is only for testing i.e. not final design
              
              DecoratedBox(
                decoration: BoxDecoration(
                  border: rightBorder(),
                ),
                child: TextButton(
                  child: itemName("Add"),
                  onPressed: () {
                    if(roleController.text == 'admin') {
                      Navigator.popAndPushNamed(context, '/add');
                    }
                  },
                ),
              ),

              
              //If login user is a patient they can use booking
              //if login user is a doctor they have schedule instead
              DecoratedBox(
                decoration: BoxDecoration(
                  border: rightBorder(),
                ),

                child: TextButton(
                  child: itemName(specificName("BOOKING", "SCHEDULE", roleController.text)),
                  onPressed: () {
                    if(roleController.text == "user") {
                      Navigator.pushNamed(context, '/booking');
                    } else if(roleController.text == "doctor") {
                      Navigator.pushNamed(context, '/schedule');
                    }
                  },
                ),
              ),

              //If login user is a patient they can view health record
              //if login user is a doctor they can prescribe medicine
              DecoratedBox(
                decoration: BoxDecoration(
                  border: rightBorder(),
                ),

                child: TextButton(
                  child: itemName(specificName("RECORD", "PRESCRIBE", roleController.text)),
                  onPressed: () {
                    if(roleController.text == "user") {
                      Navigator.pushNamed(context, '/record');
                    } else {
                      Navigator.pushNamed(context, '/prescription');
                    }
                  },
                ),
              ),

              // CHAT
              // same design regardless if patient or doctor but different routing
              DecoratedBox(
                decoration: BoxDecoration(
                  border: rightBorder(),
                ),

                child: TextButton(
                  child: itemName("CHAT"),
                  onPressed: () {
                    //example
                    // if(roleController.text == "doctor") {
                    //   Navigator.pushNamed(context, 'doctorChat');
                    // } else {
                    //   Navigator.pushNamed(context, 'userChat');
                    // }
                  },
                ),
              ),
            ],
          );
        }
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
}

Text itemName(String name) {
  return Text(
    name,
    style: const TextStyle(
      fontFamily: 'Arvo',
      color: Colors.black,
      fontSize: 16.0,
    ),
  );
}

Border rightBorder() {
  return const Border(
    right: BorderSide(color: Colors.black),
  );
}

String specificName(String userItem, String doctorItem, String role) {
  if(role == 'doctor') {
    return doctorItem;
  } else {
    return userItem;
  }
}