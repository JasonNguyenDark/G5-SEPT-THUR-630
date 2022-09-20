import 'package:flutter/material.dart';
import 'package:nd_telemedicine/Screens/record.dart';
import 'package:nd_telemedicine/Screens/signup.dart';
import 'package:nd_telemedicine/Screens/landing.dart';
import 'package:nd_telemedicine/Screens/home.dart';
import 'package:nd_telemedicine/Screens/Messaging.dart';

import 'dart:async';
import 'dart:convert';

import 'package:stomp_dart_client/stomp.dart';
import 'package:stomp_dart_client/stomp_config.dart';
import 'package:stomp_dart_client/stomp_frame.dart';

// void onConnect(StompFrame connectFrame) {
//
//   // client is connected and ready
//   stompClient.subscribe(
//     // subscription destination is in javascript
//     destination: '/topic/greetings',
//     callback: (frame) {
//       List<dynamic>? result = json.decode(frame.body!);
//       print(result);
//     },
//   );
//
// }
//
// final stompClient = StompClient(
//   config: StompConfig(
//     // this should connect to the websocket
//     url: 'ws://localhost:8081',
//     onConnect: onConnect,
//     beforeConnect: () async {
//       print('waiting to connect...');
//       await Future.delayed(Duration(milliseconds: 200));
//       print('connecting...');
//     },
//     onWebSocketError: (dynamic error) => print(error.toString()),
//     stompConnectHeaders: {'Authorization': 'Bearer yourToken'},
//     webSocketConnectHeaders: {'Authorization': 'Bearer yourToken'},
//   ),
// );

void main() {
  runApp(const MyApp());
  // stompClient.activate();
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(

      title: 'NYT',

      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),

      // default page/first page user will land on
      initialRoute: '/landing',

      // routing for frontend goes here
      routes: {
        '/landing' : (context) => const LandingPage(),
        '/signup' : (context) => const SignUpPage(),
        '/home' : (context) => const HomePage(),
        '/record' : (context) => const RecordPage(),
        '/chat' : (context) => const MessagingPage(),
      },

    );
  }
}

