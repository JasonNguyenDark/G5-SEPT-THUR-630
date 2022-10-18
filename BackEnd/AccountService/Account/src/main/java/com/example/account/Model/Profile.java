package com.example.account.Model;

// this is not a entity/table, it's just a standard model class
// use for sending fields to be displayed in profile page as well as updating account info
// basically send the updated value to db the db will return either a admin, doctor, or user result/table
public class Profile {

    private String email;

    // role = patient, doctor or admin
    private String role;

    // updated fields
    private String newEmail;

    private String newName;

    private String newSurname;

    private String newGender;

    private String newPassword;


    // setter and getters
    // current email
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    // setter/getters for fields to be updated/edited
    // new email
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    // new name
    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }

    // new surname
    public void setNewSurname(String newSurname) {
        this.newSurname = newSurname;
    }

    public String getNewSurname() {
        return newSurname;
    }

    // new gender
    public void setNewGender(String newGender) {
        this.newGender = newGender;
    }

    public String getNewGender() {
        return newGender;
    }

    // new password
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
