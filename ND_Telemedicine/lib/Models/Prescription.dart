class Prescription {
  String patientEmail;
  String patientName;
  String doctorName;
  String medicine;
  String description;
  DateTime date; //note this displays date and time can be reformatted to date only using DateUtils.dateOnly(date);

  Prescription(
    this.patientEmail,
    this.patientName,
    this.doctorName,
    this.medicine,
    this.description,
    this.date,
  );
}