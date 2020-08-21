package com.spring.reactive.loan.entity;

import java.util.Date;

public class ExceptionResponse {

private Date date;
    private  String message;
    private String result;

    public ExceptionResponse(Date date, String message, String result) {
        this.date = date;
        this.message = message;
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
