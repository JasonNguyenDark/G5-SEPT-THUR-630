package com.example.backend.stuff;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String email;

    private String start_date;

    private String end_date;

    private String start_time;

    private String end_time;

    private Integer getId(){
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


    public String getStart_Date() {
        return start_date;
    }

    public void setStart_Date(String start_date) {
        this.start_date = start_date;
    }


    public String getEnd_Date() {
        return end_date;
    }

    public void setEnd_Date(String end_date) {
        this.end_date = end_date;
    }


    public String getStart_Time() {
        return start_time;
    }

    public void setStart_Time(String start_time) {
        this.start_time = start_time;
    }


    public String getEnd_Time() {
        return end_time;
    }

    public void setEnd_Time(String end_time) {
        this.end_time = end_time;
    }

}
