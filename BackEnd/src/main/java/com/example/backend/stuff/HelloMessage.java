package com.example.backend.stuff;

//To model the message that carries the name,
// you can create a plain old Java object with a name property
// and a corresponding getName() method, as the following listing
public class HelloMessage {
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
