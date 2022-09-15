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

    private String date;

    private String startTime;

    private String duration;

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


    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getstartTime() {
        return startTime;
    }

    public void setstartTime(String startTime) {
        this.startTime = startTime;
    }


    public String getduration() {
        return duration;
    }

    public void setduration(String duration) {
        this.duration = duration;
    }

}
