package com.tutor.testtaker;

public class Loginresponse {
    String token;

    @Override
    public String toString() {
        return "Loginresponse{" +
                "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Loginresponse(String token) {
        this.token = token;
    }
}
