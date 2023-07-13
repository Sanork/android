package com.example.test;

public class UserData {
    DataClass data;
    SupportClass support;
    public void setData(DataClass data) {
        this.data = data;
    }
    public DataClass getData() {
        return data;
    }


    public void setSupport(SupportClass support) {
        this.support = support;
    }

    public SupportClass getSupport() {
        return support;
    }
    public class DataClass{

    String mail, password, name, phone, bonus, id;

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
    class SupportClass{
        String url;
        String text;
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }


    }
}
