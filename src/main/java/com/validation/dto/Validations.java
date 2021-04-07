package com.validation.dto;


public class Validations {
    String operator;
    String val;

    public Validations(String operator, String val) {
        this.operator = operator;
        this.val = val;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
