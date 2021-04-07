package com.validation.dto;

import java.util.List;

public class DataValue {
    List<Value> values;

    public DataValue(List<Value> values) {
        this.values = values;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}