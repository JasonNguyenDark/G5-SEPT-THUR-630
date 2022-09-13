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

    private Integer getid(){
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }


    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getstart_date() {
        return start_date;
    }

    public void setstart_date(String start_date) {
        this.start_date = start_date;
    }


    public String getend_date() {
        return end_date;
    }

    public void setend_date(String end_date) {
        this.end_date = end_date;
    }


    public String getstart_time() {
        return start_time;
    }

    public void setstart_time(String start_time) {
        this.start_time = start_time;
    }


    public String getend_time() {
        return end_time;
    }

    public void setend_time(String end_time) {
        this.end_time = end_time;
    }
}
