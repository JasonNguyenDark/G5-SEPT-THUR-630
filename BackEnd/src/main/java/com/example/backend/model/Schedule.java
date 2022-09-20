package com.example.backend.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    private String email;

    @Column
    private String start_date;

    @Column
    private String end_date;

    @Column
    private String start_time;

    @Column
    private String end_time;

    //getters and setter below
    //id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //start_date
    public void setStart_Date(String start_date) {
        System.out.println(start_date);
        this.start_date = start_date;
    }

    public String getStart_Date() {
        System.out.println(this.start_date);
        return start_date;
    }

    //end_date
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    //Start_time
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_time() {
        return start_time;
    }

    //End time
    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getEnd_time() {
        return end_time;
    }
}
