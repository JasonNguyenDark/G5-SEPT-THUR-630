package com.example.backend.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    // patients emails, will be used to identified prescription record
    @Column
    private String patientEmail;

    //full name
    @Column
    private String patientName;

    //full name
    @Column
    private String doctorName;

    @Column
    private String medicine;

    // description: how to use medicine aka usage
    @Column
    private String description;

    // prescribed date. note that this is java.sql date and not java.util date
    // sql default format is yyyy-MM-dd e.g. 2022-09-01
    @Column
    private Date date;

    // getters and setters
    // id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // patient email
    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    // patient name
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    // doctor name
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    // medicine
    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    // description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // date
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
