class Schedule {
  String? email;
  String? date;
  String? startTime;
  String? duration;

  Schedule({this.email, this.date, this.startTime, this.duration});

  // Schedule.fromJson(Map<String, dynamic> json) {
  //   email = json['email'];
  //   date = json['date'];
  //   startTime = json['startTime'];
  //   duration = json['duration'];
  // }

  factory Schedule.fromJson(Map<String, dynamic> json) {
    return Schedule(
    email : json['email'],
    date : json['date'],
    startTime : json['startTime'],
    duration : json['duration'],
    );
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['email'] = this.email;
    data['date'] = this.date;
    data['startTime'] = this.startTime;
    data['duration'] = this.duration;
    return data;
  }
}