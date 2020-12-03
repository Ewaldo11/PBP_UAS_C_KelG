package com.example.tubes_kelg_c.Model;

public class User {

    String fullname, username, notelp, email, password;

    public User(String fullname, String username, String notelp) {
        this.fullname = fullname;
        this.username = username;
        this.notelp = notelp;
    }

    public User(String fullname, String username, String notelp, String email, String password) {
        this.fullname = fullname;
        this.username = username;
        this.notelp = notelp;
        this.email = email;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
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

}
