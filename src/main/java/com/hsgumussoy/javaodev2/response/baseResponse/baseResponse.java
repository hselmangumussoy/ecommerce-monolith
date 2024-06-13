package com.hsgumussoy.javaodev2.response.baseResponse;

public class baseResponse {
    private int errorCode;
    private String errorMessage;

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
