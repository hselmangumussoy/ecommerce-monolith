package com.hsgumussoy.javaodev2.exception;

public class RecordNotFoundExceptions extends RuntimeException{
    private int code;
    private String message;

    public RecordNotFoundExceptions(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
