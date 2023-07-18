package com.example.test;

public class RequestChange {
    public RequestChange(String name, String phone, String birth, String gender, String city, String id) {

        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
        this.city = city;
        this.id = id;

    }

    String name;

    String birth;

    String gender;

    String city;

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

}
