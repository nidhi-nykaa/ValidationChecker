package com.validation.dto;

public class Value {
    String key;
    Object val;

    public Value(String key, Object val) {
        this.key = key;
        this.val = val;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Value{" +
                "key='" + key + '\'' +
                ", val=" + val +
                '}';
    }
}