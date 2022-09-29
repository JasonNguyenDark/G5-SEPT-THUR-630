// base url = url to springboot default port is 8080
// put global variables here

// store credentials. Alternatively shared_preference can be used but its not recommended for sensitive info
import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

const baseUrl = "http://localhost:8080/";

// for android use the following url instead and comment out the code above
// const baseUrl = "http://10.0.2.2:8080/";

const Map<String, String> headers = {
  "Content-Type": "application/json",
  "Access-Control-Allow-Origin": "*", // Required for CORS support to work, note that CORS is only used for local
};

// Credentials related stuff goes here
const credentialStorage = FlutterSecureStorage();

final TextEditingController emailCredentials = TextEditingController(text: "");
final TextEditingController passwordCredentials = TextEditingController(text: "");
final TextEditingController roleCredentials = TextEditingController(text: "");