package com.tutor.testtaker;

public class UserProfile {
    int id ;
    String username;
    String email;
    String password;
    boolean is_teacher;

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

    public UserProfile(String username, String email, String password, boolean is_teacher) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.is_teacher = is_teacher;
    }

    public boolean isIs_teacher() {
        return is_teacher;
    }

    public void setIs_teacher(boolean is_teacher) {
        this.is_teacher = is_teacher;
    }

    public UserProfile(int id, String username, String email, String password, boolean is_teacher) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.is_teacher = is_teacher;
    }

    public UserProfile(String username, String email, boolean is_teacher) {
        this.username = username;
        this.email = email;
        this.is_teacher = is_teacher;
    }
}
