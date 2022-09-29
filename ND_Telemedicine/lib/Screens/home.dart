import 'package:flutter/material.dart';

import 'package:nd_telemedicine/Globals/footer.dart';
import 'package:nd_telemedicine/Globals/appbar.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => HomePageState();
}

class HomePageState extends State<HomePage> {

  @override
  Widget build(BuildContext context) {

    return Scaffold(

      //Top bar/header
      appBar: GlobalNavBar(),

      //Footer
      bottomNavigationBar: const Footer(),

    );
  }
}