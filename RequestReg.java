package com.example.test;

public class RequestReg {
    public RequestReg(String Mail, String password, String name, String phone, String birth, String gender, String city ) {
        this.mail = Mail;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
        this.city = city;
    }

    String mail;
    String password;
    String name;

    String birth;

    String gender;

    String city;

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String phone;

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
