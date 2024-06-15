package com.hsgumussoy.javaodev2.exception;


import com.hsgumussoy.javaodev2.response.baseResponse.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler  {

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(Exception e){
        System.out.println(e.getMessage());

        BaseResponse response = new BaseResponse();
        response.setErrorCode(response.getErrorCode());
        response.setErrorMessage(response.getErrorMessage());

        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return  entity;
    }
    public ExceptionHandler() {
    }
}
