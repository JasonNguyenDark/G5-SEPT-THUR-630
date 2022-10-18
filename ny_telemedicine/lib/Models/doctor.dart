//basically the same as user since the have same columns, however
// you want different model classes. easier to update if the table
// get updated (e.g. for doctor you can included a specialization field)
class Doctor {
  String email;
  String password;
  String name;
  String surname;
  String gender;

  Doctor(
      this.email,
      this.password,
      this.name,
      this.surname,
      this.gender,
      );
}