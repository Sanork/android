package com.example.test;

public class UserData {
    DataClass data;

    public void setData(DataClass data) {
        this.data = data;
    }
    public DataClass getData() {
        return data;
    }


    public class DataClass{

    String mail;
        String password;
        String name;
        String phone;
        String bonus;
        String id;
        String birth;
        String gender;

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

        String city;

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

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


    }

}
