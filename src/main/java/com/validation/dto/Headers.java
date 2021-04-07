package com.validation.dto;

public class Headers {
    String name;
    Class dataType;
    Validations validations;

    public Headers(String name, Class dataType, Validations validations) {
        this.name = name;
        this.dataType = dataType;
        this.validations = validations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getDataType() {
        return dataType;
    }

    public void setDataType(Class dataType) {
        this.dataType = dataType;
    }

    public Validations getValidations() {
        return validations;
    }

    public void setValidations(Validations validations) {
        this.validations = validations;
    }
}
