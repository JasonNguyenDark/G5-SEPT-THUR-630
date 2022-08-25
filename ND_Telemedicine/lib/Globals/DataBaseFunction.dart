import 'dart:convert';

import 'package:http/http.dart' as http;
import '../Models/user.dart';

import 'variables.dart';
//
// class DBFunctions {
//
//   static Future<bool> signUp(String email, String password) async {
//     Map input =  ({
//       'email': email,
//       'password': password,
//     });
//
//     var body = jsonEncode(input);
//     var url = Uri.parse('${baseUrl}signUp');
//
//     http.Response response = await http.post(
//       url,
//       headers: headers,
//       body: body,
//     );
//
//     // print(url); //for debugging
//
//
//
//     return false;
//
//   }
// }
