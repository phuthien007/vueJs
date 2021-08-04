package com.backend.exception;


import org.springframework.http.HttpStatus;

public class ApiError {
    private String path;
    private String msg;
    private HttpStatus status;

    public ApiError(String path, String msg, HttpStatus status) {
        this.path = path;
        this.msg = msg;
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
