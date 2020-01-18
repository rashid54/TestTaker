package com.tutor.testtaker;

public class UserProfile {
    int id ;
    String username;
    String email;
    String password;

    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + username + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserProfile(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
