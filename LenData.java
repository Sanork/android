package com.example.test;

public class LenData {
    DataClass data;

    public void setData(DataClass data) {
        this.data = data;
    }
    public DataClass getData() {
        return data;
    }


    public class DataClass{

        String len;

        public String getLen() {
            return len;
        }

        public void setLen(String len) {
            this.len = len;
        }
    }

}

