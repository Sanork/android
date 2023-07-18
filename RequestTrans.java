package com.example.test;

public class RequestTrans {
    public RequestTrans(String id, int offset) {
        this.id = id;
        this.offset = offset;
    }
    String id;
    int offset;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}