package com.inside.hacaton_04_2023.restClasses;

public class ErrorRequest {
    private String error;

    public ErrorRequest() {}
    public ErrorRequest(String error) {
        this.error = error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "ErrorRequest{" +
                "error='" + error + '\'' +
                '}';
    }
}
