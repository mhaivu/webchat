package com.interview.webchat.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class Report {

    private Long user_id;
    private int number;
    private String email;

    public Report() {
    }

    public Report(Long user_id, int number, String email) {
        this.user_id = user_id;
        this.number = number;
        this.email = email;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
