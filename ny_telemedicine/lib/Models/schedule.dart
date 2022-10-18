class Schedule {
  int? id;
  String? email;
  String? date;
  String? startTime;
  String? duration;
  String? patientName;

  Schedule({this.id, this.email, this.date, this.startTime, this.duration, this.patientName});

  // Schedule.fromJson(Map<String, dynamic> json) {
  //   email = json['email'];
  //   date = json['date'];
  //   startTime = json['startTime'];
  //   duration = json['duration'];
  // }

  factory Schedule.fromJson(Map<String, dynamic> json) {
    return Schedule(
        id: json['id'],
        email : json['email'],
        date : json['date'],
        startTime : json['startTime'],
        duration : json['duration'],
        patientName : json['patientName']
    );
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['email'] = this.email;
    data['date'] = this.date;
    data['startTime'] = this.startTime;
    data['duration'] = this.duration;
    data['patientName']=this.patientName;
    return data;
  }
}