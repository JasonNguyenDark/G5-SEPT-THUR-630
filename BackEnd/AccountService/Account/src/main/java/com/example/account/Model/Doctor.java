package com.example.account.Model;

import javax.persistence.*;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    //getters and setters
    //id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //name(first/given name)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //surname
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    //gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

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
