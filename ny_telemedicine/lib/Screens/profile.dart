import 'dart:convert';

import 'package:flutter/material.dart';

import 'package:ny_telemedicine/Globals/footer.dart';
import 'package:ny_telemedicine/Globals/appbar.dart';
import 'package:http/http.dart' as http;
import 'package:ny_telemedicine/Screens/prescription.dart';

import '../Globals/variables.dart';
import '../Models/Profile.dart';

// assumption: everyone has access to profile, so doctor, user/patient and admin
// will have their own profile page
class ProfilePage extends StatefulWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  State<ProfilePage> createState() => ProfilePageState();
}

class ProfilePageState extends State<ProfilePage> {

  @override
  Widget build(BuildContext context) {

    return Scaffold(

      //Top bar/header
      appBar: GlobalNavBar(),

      //body/content
      body: const Content(),

      //Footer
      bottomNavigationBar: const Footer(),

    );
  }
}

class Content extends StatefulWidget {
  const  Content({super.key});

  @override
  ContentState createState() {
    return  ContentState();
  }
}

class  ContentState extends State< Content> {
  Profile profile = Profile('','','','','');

  final contactFormKey = GlobalKey<FormState>();
  final generalInfoKey = GlobalKey<FormState>();

  // form related variables

  final TextEditingController emailController = TextEditingController(text: '');
  bool emailStatus = false;

  final TextEditingController passwordController = TextEditingController(text: '');
  bool passwordStatus = false;

  final TextEditingController nameController = TextEditingController(text: '');
  bool nameStatus = false;

  final TextEditingController surnameController = TextEditingController(text: '');
  bool surnameStatus = false;

  final TextEditingController genderController = TextEditingController(text: '');
  bool genderStatus = false;

  // error messages
  String emailErrorMessage = '';
  String passwordErrorMessage = '';
  String nameErrorMessage = '';
  String surnameErrorMessage = '';
  String genderErrorMessage = '';
  String role = '';
  String emailValid = '';

  final emailRegex = RegExp(r"^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+");
  final nameRegex = RegExp(r"^([a-zA-Z,.'-])+$");

  Future<void> readFromStorage() async {
    String email = await credentialStorage.read(key: "Key_email") ?? '';
    role = await credentialStorage.read(key: "Key_role") ?? '';

    http.Response response;

    Map data = {
      'email' : email,
      'role' : role,
    };

    Uri url = Uri.parse("${baseUrl}profile/get");
    String body = jsonEncode(data);

    response = await http.post(
      url,
      headers: headers,
      body: body,
    );

    var decoder = jsonDecode(response.body);

    print(decoder);

    //Since every table has the same column name this will work
    profile.email = decoder['email'];
    profile.name = decoder['name'];
    profile.surname = decoder['surname'];
    profile.gender = decoder['gender'];
    profile.password = decoder['password'];

    // alternative way: use this if tables(in this case admin, user/patient and doctor tables)
    // have different columns names or more/less columns
    // the advantage this has over the above design is that you can name column however you want
    // if(role == 'user') { //switch statement can work
    //   profile.something = decoder['Insert name of column for user']; repeat this for each column
    // } else if(role == 'doctor') {
    //   profile.something == decoder['Insert name of column for doctor'];
    // } else if(role == admin) {
    //   profile.something == decoder['Insert name of column for admin'];
    // } else {
    //   something else if needed
    // }

    emailController.text = decoder['email'];
    nameController.text = decoder['name'];
    surnameController.text = decoder['surname'];
    genderController.text = decoder['gender'];
    passwordController.text = decoder['password'];
  }

  // update future methods

  //email
  Future<void> updateEmail() async {

    Map data = {
      'email' : profile.email,
      'role' : role,
      'newEmail' : emailController.text,
    };

    Uri url = Uri.parse("${baseUrl}profile/update/email");

    String body = jsonEncode(data);

    await http.put(
      url,
      headers: headers,
      body: body,
    );

    //get rid of current email from local storage
    await credentialStorage.delete(key: "Key_email");

    //add new email to local storage
    await credentialStorage.write(key: 'Key_email', value: emailController.text);

    profile.email = emailController.text;
  }
  Future checkEmail() async{
    http.Response results;

    Map data = {
      'email': emailController.text,
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

      emailValid = 'true';
    }
  }

  //password
  Future<void> updatePassword() async {

    Map data = {
      'email' : profile.email,
      'role' : role,
      'newPassword' : passwordController.text,
    };

    Uri url = Uri.parse("${baseUrl}profile/update/password");

    String body = jsonEncode(data);

    await http.put(
      url,
      headers: headers,
      body: body,
    );

    //get rid of current password from local storage
    await credentialStorage.delete(key: "Key_password");

    //add new email to local storage
    await credentialStorage.write(key: 'Key_password', value: passwordController.text);

    profile.password = passwordController.text;
  }

  // first name
  Future<void> updateName() async {
    Map data = {
      'email' : profile.email,
      'role' : role,
      'newName' : nameController.text,
    };

    Uri url = Uri.parse("${baseUrl}profile/update/name");

    String body = jsonEncode(data);

    await http.put(
      url,
      headers: headers,
      body: body,
    );

    profile.name = nameController.text;
  }

  //surname
  Future<void> updateSurname() async {

    Map data = {
      'email' : profile.email,
      'role' : role,
      'newSurname' : surnameController.text,
    };

    Uri url = Uri.parse("${baseUrl}profile/update/surname");

    String body = jsonEncode(data);

    await http.put(
      url,
      headers: headers,
      body: body,
    );

    profile.surname = surnameController.text;
  }

  //gender
  Future<void> updateGender() async {

    Map data = {
      'email' : profile.email,
      'role' : role,
      'newGender' : genderController.text,
    };

    Uri url = Uri.parse("${baseUrl}profile/update/gender");

    String body = jsonEncode(data);

    await http.put(
      url,
      headers: headers,
      body: body,
    );

    profile.gender = genderController.text;
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<void>(
        future: readFromStorage(),
        builder: (BuildContext context, AsyncSnapshot<void> snapshot) {
          return Padding(
            padding: const EdgeInsets.only(left: 15.0, top: 20.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [

// contact info = email, password
                Container(
                  decoration: const BoxDecoration(
                    border: Border(
                      bottom: BorderSide(color: Colors.black),
                    ),
                  ),
                  child: info("Contact Information:"),
                ),

// form for contacts
                Container(
                  padding: const EdgeInsets.only(top: 5.0),
                  child: Form(
                    key: contactFormKey,
                    child: Column(
                      children: [
// email row
                        Row(

                          children: [

                            Container(
                              width: 80.0,
                              alignment: Alignment.topLeft,
                              child: field("Email:"),
                            ),

                            Column(
                              crossAxisAlignment: CrossAxisAlignment.start,

                              children: [

                                Container(
                                  width: 150.0,
                                  margin: const EdgeInsets.only(top: 16.0),
                                  alignment: Alignment.topLeft,

                                  child: TextFormField(
                                    enabled: emailStatus,
                                    controller: emailController,
                                    decoration: formInputDeco(),

                                    style: formStyling(),
                                  ),
                                ),

                                Visibility(
                                  visible: emailStatus,
                                  child: Container(
                                    margin: const EdgeInsets.only(top: 5.0),

                                    child: errorMessage(emailErrorMessage),
                                  ),
                                ),
                              ],
                            ),

                            // buttons
                            Container(
                              padding: const EdgeInsets.only(left: 10.0),
                              alignment: Alignment.centerLeft,

                              child: Row(
                                children: [
                                  Container(
                                    margin: const EdgeInsets.only(right: 5.0),

                                    child: SizedBox(
                                      width: 60.0,

                                      child: ElevatedButton(
                                        child: Text(
                                          buttonText(emailStatus),
                                          style: const TextStyle(fontSize: 8),
                                        ),


                                        onPressed: () async {
                                          setState(() {
                                            if(emailStatus == false) {
                                              emailStatus = true;
                                            } else {
                                              emailStatus = false;
                                              emailErrorMessage = '';
                                            }
                                          });
                                        },
                                      ),
                                    ),
                                  ),

                                  Visibility(
                                    visible: emailStatus,
                                    child: ElevatedButton(
                                      child: const Text(
                                          'Save',
                                          style: TextStyle(fontSize: 8)
                                      ),
                                      onPressed: () async {
                                        await checkEmail();
                                        setState(() {
                                          if((emailController.text).isEmpty) {
                                            emailErrorMessage = 'This field cannot be empty';
                                          } else if(!emailRegex.hasMatch(emailController.text)) {
                                            emailErrorMessage = 'This email is invalid';
                                          } else if((emailController.text).replaceAll(' ', '') == profile.email) {
                                            emailErrorMessage = '';
                                            emailStatus = false;
                                          } else {
                                            if(emailValid == 'false') {
                                              emailErrorMessage = "This email is already registered";
                                            } else {
                                              updateEmail();

                                              emailErrorMessage = '';
                                              emailStatus = false;
                                              Navigator.popAndPushNamed(context, '/profile');
                                            }
                                          }
                                        });
                                      },
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ],
                        ),

//password row
                        Row(
                          children: [

                            Container(
                              width: 80.0,
                              alignment: Alignment.bottomLeft,
                              child: field("Password:"),
                            ),
// input

                            Column(
                              crossAxisAlignment: CrossAxisAlignment.start,

                              children: [

                                Container(
                                  width: 150.0,
                                  margin: const EdgeInsets.only(top: 16.0),
                                  alignment: Alignment.topLeft,

                                  child: TextFormField(
                                    obscureText: !passwordStatus,
                                    enabled: passwordStatus,
                                    controller: passwordController,
                                    decoration: formInputDeco(),

                                    style: formStyling(),
                                  ),
                                ),

                                Visibility(
                                  visible: passwordStatus,
                                  child: Container(
                                    margin: const EdgeInsets.only(top: 5.0),

                                    child: errorMessage(passwordErrorMessage),
                                  ),
                                ),
                              ],
                            ),

                            // buttons
                            Container(
                              padding: const EdgeInsets.only(left: 10.0),
                              alignment: Alignment.centerLeft,

                              child: Row(
                                children: [
                                  Container(
                                    margin: const EdgeInsets.only(right: 10.0),

                                    child: SizedBox(
                                      width: 60.0,

                                      child: ElevatedButton(
                                        child: Text(
                                          buttonText(passwordStatus),
                                          style: const TextStyle(fontSize: 8),
                                        ),

                                        onPressed: () async {
                                          setState(() {
                                            if(passwordStatus == false) {
                                              passwordStatus = true;
                                            } else {
                                              passwordStatus = false;
                                              passwordErrorMessage = '';
                                            }
                                          });
                                        },
                                      ),
                                    ),
                                  ),

                                  Visibility(
                                    visible: passwordStatus,
                                    child: ElevatedButton(
                                      child: const Text(
                                          'Save',
                                          style: TextStyle(fontSize: 8)
                                      ),

                                      onPressed: () {
                                        setState(() {
                                          if((passwordController.text).isEmpty) {
                                            passwordErrorMessage = 'This field cannot be empty';
                                          } else if((passwordController.text).length < 8) {
                                            passwordErrorMessage = 'The password must be at least 8 character long';
                                          } else if((passwordController.text).replaceAll(' ', '') == profile.password) {
                                            passwordErrorMessage = '';
                                            passwordStatus = false;
                                          } else {
                                            // update password
                                            updatePassword();


                                              passwordErrorMessage = '';
                                              passwordStatus = false;

                                              Navigator.popAndPushNamed(context, '/profile');

                                          }
                                        });
                                      },
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                ),

//General info
                Container(
                  margin: const EdgeInsets.only(top: 10.0),
                  decoration: const BoxDecoration(
                    border: Border(
                      bottom: BorderSide(color: Colors.black),
                    ),
                  ),
                  child: info("GENERAL INFORMATION:"),
                ),

//form for general info
                Container(
                  padding: const EdgeInsets.only(top: 5.0),
                  child: Form(
                    key: generalInfoKey,
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
//first name row
                        Row(
                          children: [
                            Container(
                              width: 80.0,
                              alignment: Alignment.bottomLeft,
                              child: field("First Name:"),
                            ),

//input
                            Column(
                              crossAxisAlignment: CrossAxisAlignment.start,

                              children: [

                                Container(
                                  width: 150.0,
                                  margin: const EdgeInsets.only(top: 16.0),
                                  alignment: Alignment.topLeft,

                                  child: TextFormField(
                                    enabled: nameStatus,
                                    controller: nameController,
                                    decoration: formInputDeco(),

                                    style: formStyling(),
                                  ),
                                ),

                                Visibility(
                                  visible: nameStatus,
                                  child: Container(
                                    margin: const EdgeInsets.only(top: 5.0),

                                    child: errorMessage(nameErrorMessage),
                                  ),
                                ),
                              ],
                            ),

                            // buttons
                            Container(
                              padding: const EdgeInsets.only(left: 10.0),
                              alignment: Alignment.centerLeft,

                              child: Row(
                                children: [
                                  Container(
                                    margin: const EdgeInsets.only(right: 10.0),

                                    child: SizedBox(
                                      width: 60.0,

                                      child: ElevatedButton(
                                        child: Text(
                                          buttonText(nameStatus),
                                          style: const TextStyle(fontSize: 8),
                                        ),

                                        onPressed: () async {
                                          setState(() {
                                            if(nameStatus == false) {
                                              nameStatus = true;
                                            } else {
                                              nameStatus = false;
                                              nameErrorMessage = '';
                                            }
                                          });
                                        },
                                      ),
                                    ),
                                  ),

                                  Visibility(
                                    visible: nameStatus,
                                    child: ElevatedButton(
                                      child: const Text(
                                          'Save',
                                          style: TextStyle(fontSize: 8)
                                      ),
                                      onPressed: () {
                                        setState(() {
                                          if((nameController.text).isEmpty) {
                                            nameErrorMessage = 'This field cannot be empty';
                                          } else if(!nameRegex.hasMatch(nameController.text)) {
                                            nameErrorMessage = 'This is an invalid given name';
                                          } else if((nameController.text).replaceAll(' ', '') == profile.name) {
                                            nameErrorMessage = '';
                                            nameStatus = false;
                                          } else {
                                            // update given name
                                            updateName().then((value) =>  Navigator.popAndPushNamed(context, '/profile'));


                                          }
                                        });
                                      },
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ],
                        ),

//surname row
                        Row(
                          children: [
                            Container(
                              width: 80.0,
                              alignment: Alignment.bottomLeft,
                              child: field("Surname:"),
                            ),

//input
                            Column(
                              crossAxisAlignment: CrossAxisAlignment.start,

                              children: [

                                Container(
                                  width: 150.0,
                                  margin: const EdgeInsets.only(top: 16.0),
                                  alignment: Alignment.topLeft,

                                  child: TextFormField(
                                    enabled: surnameStatus,
                                    controller: surnameController,
                                    decoration: formInputDeco(),

                                    style: formStyling(),
                                  ),
                                ),

                                Visibility(
                                  visible: surnameStatus,
                                  child: Container(
                                    margin: const EdgeInsets.only(top: 5.0),

                                    child: errorMessage(surnameErrorMessage),
                                  ),
                                ),
                              ],
                            ),

                            // buttons
                            Container(
                              padding: const EdgeInsets.only(left: 10.0),
                              alignment: Alignment.centerLeft,

                              child: Row(
                                children: [
                                  Container(
                                    margin: const EdgeInsets.only(right: 10.0),

                                    child: SizedBox(
                                      width: 60.0,

                                      child: ElevatedButton(
                                        child: Text(buttonText(surnameStatus), style: const TextStyle(fontSize: 8),),

                                        onPressed: () async {
                                          setState(() {
                                            if(surnameStatus == false) {
                                              surnameStatus = true;
                                            } else {
                                              surnameStatus = false;
                                              surnameErrorMessage = '';
                                            }
                                          });
                                        },
                                      ),
                                    ),
                                  ),

                                  Visibility(
                                    visible: surnameStatus,
                                    child: ElevatedButton(
                                      child: const Text(
                                        'Save',
                                        style: TextStyle(fontSize: 8),
                                      ),
                                      onPressed: () {
                                        setState(() {
                                          if((surnameController.text).isEmpty) {
                                            surnameErrorMessage = 'This field cannot be empty';
                                          } else if(!nameRegex.hasMatch(surnameController.text)) {
                                            surnameErrorMessage = 'This is an invalid surname';
                                          } else if((surnameController.text).replaceAll(' ', '') == profile.surname) {
                                            surnameErrorMessage = '';
                                            surnameStatus = false;
                                          } else {
                                            // update given name
                                            updateSurname().then((value) => Navigator.popAndPushNamed(context, '/profile'));


                                          }
                                        });
                                      },
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ],
                        ),

//gender row
                        Row(
                          children: [

                            Container(
                              width: 80.0,
                              alignment: Alignment.bottomLeft,
                              child: field("Gender:"),
                            ),

                            Container(
                              width: 150.0,
                              alignment: Alignment.bottomLeft,
                              child: Builder(builder: (context) {
                                if(genderStatus == false) {
                                  return TextFormField(
                                    enabled: false,
                                    controller: genderController,
                                    decoration: formInputDeco(),

                                    style: formStyling(),
                                  );
                                } else {
                                  return ButtonTheme(
                                    alignedDropdown: true,

                                    child: DropdownButtonFormField<String> (
                                      // value: genderValue,
                                      value: genderController.text,

                                      decoration: const InputDecoration(
                                        border: OutlineInputBorder(),
                                      ),

                                      onChanged: (String? value) {
                                        genderController.text = value!;
                                      },
                                      items: ['None', 'Male', 'Female', 'Other'].map<DropdownMenuItem<String>>((String value) {
                                        return DropdownMenuItem<String>(
                                          value: value,
                                          child: Text(value),
                                        );
                                      }).toList(),

                                    ),
                                  );
                                }
                              }),
                            ),


                            // buttons
                            Container(
                              padding: const EdgeInsets.only(left: 10.0),
                              alignment: Alignment.centerLeft,

                              child: Row(
                                children: [
                                  Container(
                                    margin: const EdgeInsets.only(right: 10.0),

                                    child: SizedBox(
                                      width: 60.0,

                                      child: ElevatedButton(
                                        child: Text(
                                          buttonText(genderStatus),
                                          style: const TextStyle(fontSize: 8),
                                        ),


                                        onPressed: () async {
                                          setState(() {
                                            if(genderStatus == false) {
                                              genderStatus = true;
                                            } else {
                                              genderStatus = false;
                                              genderErrorMessage = '';
                                            }
                                          });
                                        },
                                      ),
                                    )
                                  ),

                                  Visibility(
                                    visible: genderStatus,
                                    child: ElevatedButton(
                                      child: const Text('Save', style: TextStyle(fontSize: 8),),
                                      onPressed: () {
                                        setState(() {
                                          if((genderController.text).isEmpty) {
                                            genderErrorMessage = 'This field cannot be empty';
                                          } else if((genderController.text).replaceAll(' ', '') == profile.gender) {
                                            genderErrorMessage = '';
                                            genderStatus = false;
                                          } else if(genderController.text == "None" ) {
                                            genderErrorMessage = 'This field is required';
                                          } else {
                                            // update given name
                                            updateGender().then((value) =>  Navigator.popAndPushNamed(context, '/profile'));

                                          }
                                        });
                                      },
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          );
        });
  }
}

Text info(String text) {
  return Text(
    text.toUpperCase(),

    style: const TextStyle(
      fontFamily: 'Arvo',
      color: Colors.black,
      fontSize: 16.0,
      fontWeight: FontWeight.bold,
    ),
  );
}

Text field(String text) {
  return Text(
    text,

    style: formStyling(),
  );
}

TextStyle formStyling() {
  return const TextStyle(
    fontFamily: 'Arvo',
    color: Colors.black,
    fontSize: 12.0,
  );
}

InputDecoration formInputDeco() {
  return const InputDecoration(
    border: InputBorder.none,
    focusedBorder: OutlineInputBorder(),
    enabledBorder: OutlineInputBorder(),
    errorBorder: InputBorder.none,
    disabledBorder: InputBorder.none,
  );
}

// Text buttonText(bool status) {
//   return Text(() {
//     if(status == true) {
//       return ('Cancel');
//     } else {
//       return 'Edit';
//     }
//
//   }());
// }

String buttonText(bool status) {
  if(status == true) {
    return 'Cancel';
  } else {
    return 'Edit';
  }
}

Text errorMessage(String message) {
  return Text(
    message,

    style: const TextStyle(
      fontFamily: 'Arvo',
      fontSize: 10.0,
      color: Colors.red,
    ),
  );
}
