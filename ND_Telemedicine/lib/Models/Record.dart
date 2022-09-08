class Record {
  String name;
  String surname;
  String gender;
  String email;
  String allergies;
  String status;

  Record(
     this.name,
     this.surname,
     this.gender,
     this.email,
     this.allergies,
     this.status
  );

  // factory Record.fromJson(Map<String, dynamic> json) {
  //   return Record(
  //       name: json['name'],
  //       surname: json['surname'],
  //       gender: json['gender'],
  //       email: json['email'],
  //       allergies: json['password'],
  //       status: json['status'],
  //   );
  // }
}