// appBar: AppBar(
//         backgroundColor: Colors.blue,
//
//         title: FutureBuilder<void> (
//           future: readFromStorage(),
//           builder: (BuildContext context, AsyncSnapshot<void> snapshot) {
//             return Text(
//               roleController.text,
//
//               //all styling goes here
//               style: const TextStyle(
//                 fontFamily: 'Arvo',
//                 color: Colors.black,
//                 fontSize: 16.0,
//                 fontWeight: FontWeight.bold,
//               ),
//             );
//           },
//         ),
//
//           actions: <Widget>[
//               //home
//               DecoratedBox(
//                 decoration: const BoxDecoration(
//                   border: Border(
//                     left: BorderSide(color: Colors.black),
//                     right: BorderSide(color: Colors.black),
//                   ),
//                 ),
//                 child: TextButton(
//                   onPressed: () {},
//                   child: const Text(
//                     'Home',
//                     style: TextStyle(
//                       fontFamily: 'Arvo',
//                       color: Colors.black,
//                       fontSize: 16.0,
//                     ),
//                   ),
//                 ),
//               ),
//
//               // profile
//               DecoratedBox(
//                 decoration: const BoxDecoration(
//                   border: Border(
//                     left: BorderSide(color: Colors.black),
//                     right: BorderSide(color: Colors.black),
//                   ),
//                 ),
//                 child: TextButton(
//                   onPressed: () {},
//                   child: const Text(
//                       'profile',
//                     style: TextStyle(
//                       fontFamily: 'Arvo',
//                       color: Colors.black,
//                       fontSize: 16.0,
//                     ),
//                   ),
//                 ),
//               ),
//
//             // Booking/Scheduling
//             DecoratedBox(
//                 decoration: const BoxDecoration(
//                   border: Border(
//                     left: BorderSide(color: Colors.black),
//                     right: BorderSide(color: Colors.black),
//                   ),
//                 ),
//                 child: FutureBuilder<void>(
//                     future: readFromStorage(),
//                     builder:
//                         (BuildContext context, AsyncSnapshot<void> snapshot) {
//                       if (roleController.text == 'doctor') {
//                         return TextButton(
//                           onPressed: () {},
//                           child: const Text(
//                             'Schedule',
//                             style: TextStyle(
//                               fontFamily: 'Arvo',
//                               color: Colors.black,
//                               fontSize: 16.0,
//                             ),
//                           ),
//                         );
//                       } else {
//                         return TextButton(
//                           onPressed: () {},
//                           child: const Text(
//                             'Booking',
//                             style: TextStyle(
//                               fontFamily: 'Arvo',
//                               color: Colors.black,
//                               fontSize: 16.0,
//                             ),
//                           ),
//                         );
//                       }
//                     })
//             ),
//
//             // Health Record/Prescription
//             DecoratedBox(
//                 decoration: const BoxDecoration(
//                   border: Border(
//                     left: BorderSide(color: Colors.black),
//                     right: BorderSide(color: Colors.black),
//                   ),
//                 ),
//                 child: FutureBuilder<void>(
//                     future: readFromStorage(),
//                     builder:
//                         (BuildContext context, AsyncSnapshot<void> snapshot) {
//                       if (roleController.text == 'doctor') {
//                         return TextButton(
//                           onPressed: () {},
//                           child: const Text(
//                             'Prescribe',
//                             style: TextStyle(
//                               fontFamily: 'Arvo',
//                               color: Colors.black,
//                               fontSize: 16.0,
//                             ),
//                           ),
//                         );
//                       } else {
//                         return TextButton(
//                           onPressed: () {Navigator.pushNamed(context, '/record');},
//                           child: const Text(
//                             'Record',
//                             style: TextStyle(
//                               fontFamily: 'Arvo',
//                               color: Colors.black,
//                               fontSize: 16.0,
//                             ),
//                           ),
//                         );
//                       }
//                     })),
//
//             // Chat
//             DecoratedBox(
//               decoration: const BoxDecoration(
//                 border: Border(
//                   left: BorderSide(color: Colors.black),
//                   right: BorderSide(color: Colors.black),
//                 ),
//               ),
//               child: TextButton(
//                 onPressed: () {},
//                 child: const Text(
//                   'Chat',
//                   style: TextStyle(
//                     fontFamily: 'Arvo',
//                     color: Colors.black,
//                     fontSize: 16.0,
//                   ),
//                 ),
//               ),
//             ),
//           ]
//       ),