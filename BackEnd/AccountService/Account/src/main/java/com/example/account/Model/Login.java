package com.example.account.Model;

// Not a entity/table get login credentials
public class Login {
    private String email;

    private String password;

    //email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
