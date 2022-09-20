package com.example.backend.model;

import javax.persistence.*;


// Atm this is the same as user however in the case which doctor may need to provide
// other fields(e.g. something like Employee ID, doctor's license etc.) then it easier to have
// separate tables for user and doctor
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String gender;

    @Column
    private String email;

    @Column
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