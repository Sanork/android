package com.example.test;

public class CountData {
    DataClass data;

    public void setData(DataClass data) {
        this.data = data;
    }
    public DataClass getData() {
        return data;
    }


    public class DataClass{

        String count;

        public String getCount() {
            return count;
        }

        public void setLen(String count) {
            this.count = count;
        }
    }

}
