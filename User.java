package com.example.test;

public class User {

    private String Name;
    private String Phone;
    private String Mail;
    private String Password;


    public User(String name, String phone, String mail, String password) {
        Name = name;
        Phone = phone;
        Mail = mail;
        Password = password;
    }

    public User() {

    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }



}
