package com.restful.booker.model;

import io.restassured.response.ValidatableResponse;

public class RestfulBookerAuthPojo {

    private String username;
    private String password;

    public ValidatableResponse getUsername(String username, String password) {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
