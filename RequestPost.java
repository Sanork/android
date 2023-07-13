package com.example.test;

public class RequestPost {
    public RequestPost(String Mail, String password) {
        this.mail = Mail;
        this.password = password;
    }

    String mail, password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
