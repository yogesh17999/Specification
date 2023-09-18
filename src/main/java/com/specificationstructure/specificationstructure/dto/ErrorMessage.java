package com.specificationstructure.specificationstructure.dto;

import org.springframework.http.HttpStatus;

public class ErrorMessage extends ApiResponse{

    public ErrorMessage(HttpStatus status, String message, Object data)
    {
        super(status, message, data);
    }
    public ErrorMessage(HttpStatus status, String message, String error){
        super(status, message, error);
    }

    public ErrorMessage(HttpStatus status,String message)
    {
        super(status,message);
    }
}
