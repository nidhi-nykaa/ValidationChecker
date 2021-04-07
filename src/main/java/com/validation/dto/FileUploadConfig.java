package com.validation.dto;

import java.util.List;

public class FileUploadConfig {
    List<Headers> headers;
    List<DataValue>dataValues;

    public FileUploadConfig(List<Headers> headers, List<DataValue> dataValues) {
        this.headers = headers;
        this.dataValues = dataValues;
    }

    public List<Headers> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Headers> headers) {
        this.headers = headers;
    }

    public List<DataValue> getDataValues() {
        return dataValues;
    }

    public void setDataValues(List<DataValue> dataValues) {
        this.dataValues = dataValues;
    }
}
