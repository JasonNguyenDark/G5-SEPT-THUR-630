import 'package:flutter/material.dart';
import 'package:ny_telemedicine/Globals/variables.dart';

// For landing page and sign up
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
          fontSize: 24.0,
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
          if((roleController.text).toLowerCase() == "admin") {
            return AppBar(
              actions: <Widget> [

                // HOME
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

                DecoratedBox(
                  decoration: BoxDecoration(
                    border: rightBorder(),
                  ),

                  child: TextButton(
                    child: itemName("Log Out"),
                    onPressed: () async {
                      await credentialStorage.deleteAll();
                      Navigator.popAndPushNamed(context, '/landing');
                    },
                  ),
                ),
              ],
            );
          } else if((roleController.text).toLowerCase() == "doctor") {
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


                //If login user is a patient they can use booking
                //if login user is a doctor they have schedule instead
                DecoratedBox(
                  decoration: BoxDecoration(
                    border: rightBorder(),
                  ),

                  child: TextButton(
                    child: itemName("SCHEDULE"),
                    onPressed: () {
                      Navigator.popAndPushNamed(context, '/schedule');
                    },
                  ),
                ),

                //prescription
                DecoratedBox(
                  decoration: BoxDecoration(
                    border: rightBorder(),
                  ),

                  child: TextButton(
                    child: itemName("PRESCRIBE"),
                    onPressed: () {
                      Navigator.pushNamed(context, '/prescription');
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
                    onPressed: () {},
                  ),
                ),

                //sighout
                DecoratedBox(
                  decoration: BoxDecoration(
                    border: rightBorder(),
                  ),

                  child: TextButton(
                    child: itemName("Log Out"),
                    onPressed: () async {
                      await credentialStorage.deleteAll();
                      Navigator.popAndPushNamed(context, '/landing');
                    },
                  ),
                ),
              ],
            );
          } else {
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

                //If login user is a patient they can use booking
                //if login user is a doctor they have schedule instead
                DecoratedBox(
                  decoration: BoxDecoration(
                    border: rightBorder(),
                  ),

                  child: TextButton(
                    child: itemName("BOOKING"),
                    onPressed: () {
                      Navigator.popAndPushNamed(context, "/booking");
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
                    child: itemName("RECORD"),
                    onPressed: () {
                      Navigator.pushNamed(context, '/record');
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
                    onPressed: () {},
                  ),
                ),

                //sign out
                DecoratedBox(
                  decoration: BoxDecoration(
                    border: rightBorder(),
                  ),

                  child: TextButton(
                    child: itemName("Log Out"),
                    onPressed: () async {
                      await credentialStorage.deleteAll();
                      Navigator.popAndPushNamed(context, '/landing');
                    },
                  ),
                ),
              ],
            );
          }
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
      fontSize: 10.0,
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