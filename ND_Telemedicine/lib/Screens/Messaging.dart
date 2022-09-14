import 'dart:js';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:nd_telemedicine/Globals/variables.dart';

class Messaging extends StatefulWidget {
  const Messaging({Key? key}) : super(key: key);

  @override
  State<Messaging> createState() => MessagingPageState();
}

class MessagingPageState extends State<Messaging> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      //  TODO: include java_script for front end to function.

    /* <html>
    <head>
    <title>Hello WebSocket</title>
    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/main.css" rel="stylesheet">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/app.js"></script>
    </head>
    <body>
    <noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>
    <div id="main-content" class="container">
    <div class="row">
    <div class="col-md-6">
    <form class="form-inline">
    <div class="form-group">
    <label for="connect">WebSocket connection:</label>
    <button id="connect" class="btn btn-default" type="submit">Connect</button>
    <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
    </button>
    </div>
    </form>
    </div>
    <div class="col-md-6">
    <form class="form-inline">
    <div class="form-group">
    <label for="name">What is your name?</label>
    <input type="text" id="name" class="form-control" placeholder="Your name here...">
    </div>
    <button id="send" class="btn btn-default" type="submit">Send</button>
    </form>
    </div>
    </div>
    <div class="row">
    <div class="col-md-12">
    <table id="conversation" class="table table-striped">
    <thead>
    <tr>
    <th>Greetings</th>
    </tr>
    </thead>
    <tbody id="greetings">
    </tbody>
    </table>
    </div>
    </div>
    </div>
    </body>
    </html> */

    );
  }
}

//duplicate footer class
class Footer extends StatefulWidget {
  const Footer({Key? key}) : super(key: key);

  @override
  State<Footer> createState() => FooterState();
}

//duplicate footer class
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
