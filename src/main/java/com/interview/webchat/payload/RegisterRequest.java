package com.interview.webchat.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {
    private String email;

    @JsonProperty("first_name")
    private String fistName;

    @JsonProperty("last_name")
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
