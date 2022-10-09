class booking {
  String? email;
  int? scheduleId;

  booking({this.email, this.scheduleId});

  booking.fromJson(Map<String, dynamic> json) {
    email = json['email'];
    scheduleId = json['scheduleId'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['email'] = this.email;
    data['scheduleId'] = this.scheduleId;
    return data;
  }
}