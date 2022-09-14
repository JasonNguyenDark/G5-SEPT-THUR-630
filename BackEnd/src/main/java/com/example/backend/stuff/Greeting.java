package com.example.backend.stuff;

//To model the greeting representation,
// add another plain old Java object with a content property and a corresponding getContent()
public class Greeting {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
