package com.example.account.Model;

import javax.persistence.*;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "date")
    private String date;

    @Column(name = "startTime")
    private String startTime;

    @Column(name = "duration")
    private String duration;

    @Column(name = "patientName")
    private String patientName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getpatientName() {
        return patientName;
    }

    public void setpatientName(String patientName) {
        this.patientName = patientName;
    }
}
